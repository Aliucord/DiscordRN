import { defineConfig } from "rollup";
import esbuild from "rollup-plugin-esbuild";
import { string } from "rollup-plugin-string";

export default defineConfig({
    input: "src/index.js",
    output: [
        { file: "../android/app/src/main/assets/preload.js", format: "cjs" }
    ],
    plugins: [
        string({
            include: "**/*.txt"
        }),
        esbuild({ target: "es2015" })
    ]
});
