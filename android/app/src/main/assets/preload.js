globalThis = new Proxy(
    globalThis,
    {
        set: function (obj, prop, value) {
            if (prop === "__d") {
                obj[prop] = function (factory, moduleId, dependencyMap) {
                    if (moduleId === 13) {
                        const _factory = factory;
                        factory = function (global, _$$_REQUIRE, _$$_IMPORT_DEFAULT, _$$_IMPORT_ALL, module, exports, _dependencyMap) {
                            const ret = _factory(global, _$$_REQUIRE, _$$_IMPORT_DEFAULT, _$$_IMPORT_ALL, module, exports, _dependencyMap);
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
                            return ret;
                        };
                    }

                    return value(factory, moduleId, dependencyMap);
                }
            } else {
                obj[prop] = value;
            }

            return true;
        }
    },
);