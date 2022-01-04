// https://github.com/aveq-research/localforage-asyncstorage-driver/blob/master/src/main.js

/**
 * This implementation adds localforage support for react-native async storage,
 *  as documented in: https://facebook.github.io/react-native/docs/asyncstorage
 *
 * The implementation itself is based on the already existing localstorage
 *  support implementation. A future work could try to unite similar code
 *  structures of both implementations.
 */

let AsyncStorage;

async function _withCallback(callback, promise) {
    try {
        const ret = await promise();

        if (callback) {
            callback(null, ret);
        }

        return ret;
    }
    catch (e) {
        if (callback) {
            callback(e);
        }

        throw e;
    }
}

function _getKeyPrefix(options, defaultConfig) {
    let keyPrefix = `${options.name}/`;
    if (options.storeName && options.storeName !== defaultConfig.storeName) {
        keyPrefix += `${options.storeName}/`;
    }

    return keyPrefix;
}

async function _iterate(dbInfo, keys, iterator, iterationNumber) {
    const key = keys.shift();
    if (key === undefined) {
        return;
    }

    const keyPrefix = dbInfo.keyPrefix;
    if (key.indexOf(keyPrefix) === 0) {
        const serializedValue = await AsyncStorage.getItem(key);

        // If a result was found, parse it from the serialized
        // string into a JS object. If result isn't truthy, the
        // key is likely undefined and we'll pass it straight
        // to the iterator.
        const value = serializedValue && dbInfo.serializer
            ? dbInfo.serializer.deserialize(serializedValue)
            : serializedValue;

        const itVal = iterator(
            value,
            key.substring(keyPrefix.length),
            iterationNumber++
        );

        if (itVal !== undefined) {
            return itVal;
        }
    }

    return _iterate(dbInfo, keys, iterator, iterationNumber);
}

const defaultDriver = {

    /**
     * The name of this driver!
     */
    _driver: 'rnAsyncStorageWrapper',

    /**
     * Returns true if the driver can support the React Native Async Storage on the executed platform, false otherwise.
     * @returns {boolean}
     * @private
     */
    // eslint-disable-next-line require-await
    _support: async function () {
        try {
            return (
                typeof AsyncStorage !== 'undefined' &&
                'setItem' in AsyncStorage &&
                !!AsyncStorage.setItem
            );
        }
        catch (e) {
            return false;
        }
    },

    /**
     * Config the localStorage backend, using options set in the config.
     * @param serializer
     * @param options
     * @returns {Promise<void>}
     * @private
     */
    _initStorage: async function (serializer, options = {}) {
        const dbInfo = Object.assign({}, options);
        dbInfo.keyPrefix = _getKeyPrefix(options, this._defaultConfig);

        const localStorageTestKey = '_localforage_support_test';
        await AsyncStorage.setItem(localStorageTestKey, 'it-works');
        await AsyncStorage.removeItem(localStorageTestKey);
        // if these operations throw, the storage initialization fails, because something is wrong with the storage!

        if (serializer === undefined) {
            serializer = await this.getSerializer();
        }

        if (serializer) {
            dbInfo.serializer = serializer;
        }

        this._dbInfo = dbInfo;
        return Promise.resolve();
    },

    /**
     * Iterate over all items in the store.
     *
     * @param iterator the method to execute on each item individually!
     * @param callback
     * @returns {Promise<T>}
     */
    iterate: function (iterator, callback) {
        return _withCallback(callback, async () => {
            await this.ready();

            const allKeys = await AsyncStorage.getAllKeys();
            return _iterate(this._dbInfo, allKeys, iterator, 0);
        });
    },

    /**
     * Retrieve an item from the store. Unlike the original async_storage
     * library in Gaia, we don't modify return values at all. If a key's value
     * is `undefined`, we pass that value to the callback function.
     *
     * @param key
     * @param callback
     * @returns {Promise<T>}
     */
    getItem: function (key, callback) {
        return _withCallback(callback, async () => {
            key = String(key);
            await this.ready();
            const dbInfo = this._dbInfo;
            const item = await AsyncStorage.getItem(`${dbInfo.keyPrefix}${key}`);

            return (dbInfo.serializer && item) ? dbInfo.serializer.deserialize(item) : item;
        });
    },

    /**
     * Set a key's value and run an optional callback once the value is set.
     * Unlike Gaia's implementation, the callback function is passed the value,
     * in case you want to operate on that value only after you're sure it
     * saved, or something like that.
     *
     * @param key
     * @param value
     * @param callback
     * @returns {Promise<T>}
     */
    setItem: function (key, value, callback) {
        return _withCallback(callback, async () => {
            key = String(key);
            await this.ready();
            // Convert undefined values to null.
            // https://github.com/mozilla/localForage/pull/42
            if (value === undefined) {
                value = null;
            }

            // Save the original value to pass to the callback.
            const originalValue = value; //_copyValue(value);
            const dbInfo = this._dbInfo;

            return new Promise((resolve, reject) => {
                async function writeToStorage(valueToWrite) {
                    await AsyncStorage.setItem(`${dbInfo.keyPrefix}${key}`, valueToWrite);
                    return originalValue;
                }

                if (dbInfo.serializer) {
                    dbInfo.serializer.serialize(value, (serializedValue, error) => {
                        if (error) {
                            reject(error);
                            return;
                        }

                        writeToStorage(serializedValue)
                            .then(resolve)
                            .catch(reject);
                    });
                }
                else {
                    writeToStorage(value)
                        .then(resolve)
                        .catch(reject);
                }
            });
        });
    },

    /**
     * Remove an item from the store, nice and simple.
     * @param key
     * @param callback
     * @returns {Promise<T>}
     */
    removeItem: function (key, callback) {
        return _withCallback(callback, async () => {
            key = String(key);
            await this.ready();
            await AsyncStorage.removeItem(`${this._dbInfo.keyPrefix}${key}`);
        });
    },

    /**
     * Remove all keys from the datastore, effectively destroying all data in
     * the app's key/value store!
     *
     * @param callback
     * @returns {Promise<T>}
     */
    clear: function (callback) {
        return _withCallback(callback, async () => {
            await this.ready();
            const keyPrefix = this._dbInfo.keyPrefix;
            const keysToDelete = [];
            const allKeys = await AsyncStorage.getAllKeys();

            for (const key of allKeys) {
                if (key.indexOf(keyPrefix) === 0) {
                    keysToDelete.push(key);
                }
            }

            await AsyncStorage.multiRemove(keysToDelete);
        });
    },

    /**
     * Supply the number of keys in the datastore to the callback function.
     *
     * @param callback
     * @returns {PromiseLike<any> | Promise<any>}
     */
    length: function (callback) {
        return _withCallback(callback, async () => {
            const keys = await this.keys();
            return keys.length;
        });
    },

    /**
     * Same as localStorage's key() method, except takes a callback.
     * @returns {Promise<*>}
     */
    key: function (n, callback) {
        return _withCallback(callback, async () => {
            await this.ready();
            const dbInfo = this._dbInfo;
            const allKeys = await AsyncStorage.getAllKeys();
            const key = allKeys[n];

            return key ? key.substring(dbInfo.keyPrefix.length) : null;
        });
    },

    keys: function (callback) {
        return _withCallback(callback, async () => {
            await this.ready();

            const dbInfo = this._dbInfo;
            const allKeys = await AsyncStorage.getAllKeys();
            const driverKeys = [];

            for (const key of allKeys) {
                if (key.indexOf(dbInfo.keyPrefix) === 0) {
                    driverKeys.push(key.substring(dbInfo.keyPrefix.length));
                }
            }

            return driverKeys;
        });
    },

    dropInstance: function (options = {}, callback) {
        return _withCallback(callback, async () => {
            const currentConfig = this.config();
            options.name = options.name || currentConfig.name;
            options.storeName = options.storeName || currentConfig.storeName;

            if (options.name === undefined) {
                throw Error('Invalid arguments');
            }

            const keyPrefix = _getKeyPrefix(options, this._defaultConfig);
            const keys = await this.keys();
            const keysToDelete = keys.map(k => `${keyPrefix}${k}`);

            return AsyncStorage.multiRemove(keysToDelete);
        });
    }

};

function driverWithSerialization(serializer) {
    const driver = Object.assign({}, defaultDriver, {
        _driver: `${defaultDriver._driver}-with${serializer === undefined ? 'DefaultSerializer' : (serializer === null ? 'outSerializer' : 'Serializer')}`,
    });

    const orgInitFunctionBody = driver._initStorage;
    driver._initStorage = function (options) {
        return orgInitFunctionBody.apply(this, [serializer, options]);
    };

    return driver;
}

function driverWithoutSerialization() {
    return driverWithSerialization(null);
}

module.exports = {
    driverWithoutSerialization,
    driverWithSerialization,
    AsyncStorage
}
