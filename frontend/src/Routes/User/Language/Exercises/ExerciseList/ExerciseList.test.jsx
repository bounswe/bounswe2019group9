import React from 'react';
import { MemoryRouter } from 'react-router-dom';

import { render, fireEvent, waitForElement } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

import ExerciseList from ".";
import {languages} from "../../../../../Helpers/languages";

test('writes loading ... when loading', () => {
    const { container, getByText } = render(
        <ExerciseList
            loading={true}
            language={languages[0]}
            exercises={[]}
        />,
        { wrapper: MemoryRouter }
    );

    expect(getByText(/loading\s*\.\.\./i)).toBeInTheDocument();
});
