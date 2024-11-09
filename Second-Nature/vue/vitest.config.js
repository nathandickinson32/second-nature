import { defineConfig } from 'vitest/config';
import vue from '@vitejs/plugin-vue';

export default defineConfig({
  plugins: [vue()],
  test: {
    globals: true,  // Enable global test APIs (e.g., expect(), describe(), it())
    environment: 'jsdom',  // or 'jsdom' for browser-like environment
    coverage: {
      provider: 'c8',
      reporter: ['text', 'json', 'html'],  // Code coverage reporting formats
    },
  },
});