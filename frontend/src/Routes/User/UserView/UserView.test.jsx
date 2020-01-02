import React from 'react';
import { MemoryRouter } from 'react-router-dom';

import { render, fireEvent, waitForElement } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

import UserView from ".";

test('writes name', () => {
  const { container, getByText } = render(
    <UserView
      store={{userId: 11}}
      userId={12}
      firstName={"Egemen"}
      lastName={"Göl"}
      languages={["English", "German"]}
      grades={[4,5]}
      progressLevels={[3,4]}
    />,
    {wrapper: MemoryRouter}
  );

  expect(getByText("Egemen Göl")).toBeInTheDocument();
});

