/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        white: "#fff",
        black: "#000",
        green: "#004239",
        mediumGreen: "#1cd174",
        lightGreen: "#93eb33",
        dark: "#34373C",
        blue: "#1F41BB",
        gray: "#FFFFFE",
        lightGrey: "#F1F2F3",
        darkGray: "#A8A8A8",
        error: "#ff3333",
        orange: "#FFA500",
      }
    },
  },
  plugins: [],
}

