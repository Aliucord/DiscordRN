import * as driver from "./driver";
import reactDevToolsCode from "./react-devtools-core.txt";

const loggerId = 13;
const promiseId = 111;
const nativeModulesId = 41;
const localForageId = 892;
const setUpBatchedBridgeId = 152;
const exceptionsManagerId = 99;

const appStateId = 415;
const viewConfigId = 183;
const flattenStyleId = 164;

globalThis = new Proxy(
    globalThis,
    {
        set: function (obj, prop, value) {
            if (prop === "__d") {
                obj[prop] = function (factory, moduleId, dependencyMap) {
                    if (moduleId === loggerId || moduleId == promiseId || moduleId == localForageId || moduleId == setUpBatchedBridgeId || moduleId == exceptionsManagerId) {
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
                                    logger.error = (s) => console.error(prefix + (s && s.stack ? s.stack : s));
                                    logger.trace = (s) => console.log(prefix + s);
                                    logger.verbose = (s) => console.log(prefix + s);
                                    return logger;
                                }
                            } else if (moduleId == promiseId) {
                                module.exports = new Proxy(module.exports, {
                                    construct: function (target, argumentsList, newTarget) {
                                        const promise = new target(...argumentsList);

                                        if (argumentsList.length > 0 && argumentsList[0] !== Promise._0) {
                                            const stack = new Error("Promise not resolved after 10 seconds").stack;
                                            setTimeout(() => {
                                                if (promise._V === 0) {
                                                    console.warn(stack);
                                                }
                                            }, 10000);
                                        }

                                        return promise;
                                    }
                                });

                                module.exports._Z = (promise, error) => {
                                    console[error instanceof Error ? "error" : "warn"](`Promise rejection (_deferredState: ${promise._U})\n` + (error instanceof Error ? error.stack : new Error(typeof error === "object" ? JSON.stringify(error) : error).stack));
                                };
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
                            } else if (moduleId == setUpBatchedBridgeId) {
                                (0, eval)(reactDevToolsCode);
                                const reactDevTools = require_react_devtools_core();

                                function setUpReactDevTools() {
                                    const AppState = _$$_REQUIRE(appStateId);
                                    const isAppActive = () => AppState.currentState !== "background";

                                    const ws = new WebSocket("ws://localhost:8097");
                                    ws.addEventListener("close", function (event) {
                                        console.log("Devtools connection closed: " + event.message);
                                    });

                                    const viewConfig = _$$_REQUIRE(viewConfigId);
                                    const { flattenStyle } = _$$_REQUIRE(flattenStyleId);
                                    console.log("Connecting to devtools");
                                    reactDevTools.connectToDevTools({
                                        isAppActive,
                                        resolveRNStyle: flattenStyle,
                                        nativeStyleEditorValidAttributes: viewConfig.validAttributes.style ? Object.keys(
                                            viewConfig.validAttributes.style,
                                        ) : undefined,
                                        websocket: ws
                                    });
                                }

                                setUpReactDevTools();

                                const { NativeAppEventEmitter } = _$$_REQUIRE(17);
                                NativeAppEventEmitter.addListener("RCTDevMenuShown", setUpReactDevTools);
                            } else if (moduleId == exceptionsManagerId) {
                                module.exports.handleException = (e, isFatal) => {
                                    if (e instanceof Error) {
                                        console.error(e.stack);
                                    } else {
                                        console.error(e);
                                    }
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
