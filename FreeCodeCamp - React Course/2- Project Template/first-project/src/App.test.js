// This is a test file for the React application. It uses the React Testing Library to render the App component and check if it contains a link with the text "learn react". The test will pass if the link

import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
