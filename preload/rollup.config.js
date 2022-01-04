import { defineConfig } from "rollup";
import commonjs from "@rollup/plugin-commonjs";
import esbuild from "rollup-plugin-esbuild";

export default defineConfig({
    input: "src/index.js",
    output: [
        { file: "../android/app/src/main/assets/preload.js", format: "cjs" }
    ],
    plugins: [
        commonjs(),
        esbuild({ target: "es2015" })
    ]
});
