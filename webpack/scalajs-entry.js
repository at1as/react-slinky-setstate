if (process.env.NODE_ENV === "production") {
    const opt = require("./test-set-state-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./test-set-state-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./test-set-state-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
