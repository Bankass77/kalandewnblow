/** @type {import('tailwindcss').Config} */
const defaultTheme = require('tailwindcss/defaultTheme');

module.exports = {
	purge: {
		content: ['./src/main/resources/templates/**/*.html']
	},

	darkMode: false,

	theme: {
		extend: {
			fontFamily: {
				sans: ['Inter var', ...defaultTheme.fontFamily.sans]
			}
		},
	},

	variants: {
		extend: {},
	},
	// tailwind.config.js
	plugins: [
		require("@tailwindcss/forms")({
			strategy: 'base', // only generate global styles
			strategy: 'class', // only generate classes
		}),
	],
}
