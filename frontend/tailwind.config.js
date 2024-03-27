/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
    colors: {
      'primary': '#7F9F80',
      'secondary': '#AECC9C',
      'tertiary': '#E08A39',
      'quaternary': '#F1B252',
      'quinary': '#F7F0B1',
      'light': '#EEEEEE',
      'dark': '#1C1C1E',
      'grey5': '#7F7F7F'
    },
    fontFamily: {
      'title':['Poppins', 'sans-serif', 'ui-sans-serif'],
      'body':['Ubuntu', 'sans-serif', 'ui-sans-serif']
    },
  },
  plugins: [],
}
