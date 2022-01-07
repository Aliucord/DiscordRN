import driver from "./driver";

const loggerId = 13;
const promiseId = 111;
const nativeModulesId = 41;
const localForageId = 892;

globalThis = new Proxy(
    globalThis,
    {
        set: function (obj, prop, value) {
            if (prop === "__d") {
                obj[prop] = function (factory, moduleId, dependencyMap) {
                    if (moduleId === loggerId || moduleId == promiseId || moduleId == localForageId) {
                        const _factory = factory;
                        factory = function (global, _$$_REQUIRE, _$$_IMPORT_DEFAULT, _$$_IMPORT_ALL, module, exports, _dependencyMap) {
                            const ret = _factory(global, _$$_REQUIRE, _$$_IMPORT_DEFAULT, _$$_IMPORT_ALL, module, exports, _dependencyMap);

                            if (moduleId === loggerId) {
                                const _logger = module.exports.default;
                                module.exports.default = function (name) {
                                    const logger = new _logger(name);
                                    const prefix = `[${name}] `;
                                    logger.log = (s) => console.log(prefix + s);
                                    logger.warn = (s) => console.warn(prefix + s);
                                    logger.error = (s) => console.error(prefix + s);
                                    logger.trace = (s) => console.log(prefix + s);
                                    logger.verbose = (s) => console.log(prefix + s);
                                    return logger;
                                }
                            } else if (moduleId == promiseId) {
                                const _then = module.exports.prototype.then;
                                module.exports.prototype.then = function (onFulfilled, onRejected) {
                                    let isDone = false;
                                    const stack = (new Error("PROMISE TIMEOUT")).stack;
                                    setTimeout(() => {
                                        if (!isDone) {
                                            console.log(stack);
                                        }
                                    }, 10000);
                                    _then.apply(this, [() => { isDone = true }, () => { isDone = true }]);
                                    return _then.apply(this, [onFulfilled, reason => {
                                        isDone = true;
                                        console[onRejected ? "warn" : "error"]("Promise errored: " + reason.stack)
                                        if (onRejected) {
                                            onRejected(reason);
                                        }
                                    }]);
                                }
                            } else if (moduleId == localForageId) {
                                driver.AsyncStorage = _$$_REQUIRE(nativeModulesId).RNC_AsyncSQLiteDBStorage;
                                const _driver = driver.driverWithoutSerialization();
                                module.exports.defineDriver(_driver).then(() => module.exports.setDriver(_driver._driver));

                                module.exports.__proto__.getDriver = function getDriver(t, n, o) {
                                    const d = Promise.resolve(_driver);
                                    typeof n == "function" && d.then(n);
                                    typeof o == "function" && d.catch(o);
                                    return d;
                                };
                            }

                            return ret;
                        };
                    }

                    return value(factory, moduleId, dependencyMap);
                }
            } else {
                obj[prop] = value;
            }

            return true;
        },
    },
);
