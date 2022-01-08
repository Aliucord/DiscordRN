import * as driver from "./driver";
import reactDevToolsCode from "./react-devtools-core.txt";

const loggerId = 13;
const promiseId = 111;
const nativeModulesId = 41;
const localForageId = 892;
const setUpBatchedBridgeId = 152;

const appStateId = 415;
const viewConfigId = 183;
const flattenStyleId = 164;

globalThis = new Proxy(
    globalThis,
    {
        set: function (obj, prop, value) {
            if (prop === "__d") {
                obj[prop] = function (factory, moduleId, dependencyMap) {
                    if (moduleId === loggerId || moduleId == promiseId || moduleId == localForageId || moduleId == setUpBatchedBridgeId) {
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
                                        console[onRejected ? "warn" : "error"]("Promise errored: " + reason ? (reason.stack ? reason.stack : reason) : new Error().stack)
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
