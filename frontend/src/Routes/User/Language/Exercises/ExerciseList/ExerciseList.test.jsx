import React from 'react';
import { MemoryRouter } from 'react-router-dom';

import { render, fireEvent, wait, waitForElement } from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect'

import ExerciseList from ".";
import {languages} from "../../../../../Helpers/languages";
import {exerciseTypes} from "../../../../../Helpers/exercises";

jest.useFakeTimers();

describe('Exercise List Component', () => {
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

    const sampleExercises = [{
        id: 123,
        languageId: languages[0].languageId,
        grade: 3,
        typeId: exerciseTypes[1].typeId,
        questionBody: 'How many cans can can a can?',
        optionA: 'One',
        optionB: 'Two',
        optionC: 'Three',
        optionD: 'Four',
        correctAnswer: 1,
        tags: []
    }, {
        id: 124,
        languageId: languages[0].languageId,
        grade: 3,
        typeId: exerciseTypes[0].typeId,
        soundUrl: 'sample.mp3',
        questionBody: 'What\'s the meaning of life?',
        optionA: '42',
        optionB: 'Firetruck',
        optionC: 'Potatoes',
        optionD: 'Money',
        correctAnswer: 1,
        tags: []
    }, {
        id: 125,
        languageId: languages[0].languageId,
        grade: 3,
        typeId: exerciseTypes[3].typeId,
        imageUrl: 'sample.png',
        questionBody: 'What\'s the meaning of life?',
        optionA: '42',
        optionB: 'Firetruck',
        optionC: 'Potatoes',
        optionD: 'Money',
        correctAnswer: 1,
        tags: []
    }, {
        id: 126,
        languageId: languages[0].languageId,
        grade: 3,
        typeId: exerciseTypes[2].typeId,
        questionBody: 'What\'s the meaning of life?',
        optionA: '42',
        optionB: 'Firetruck',
        optionC: 'Potatoes',
        optionD: 'Money',
        correctAnswer: 1,
        tags: [
            { id: 1, exerciseId: 126, tagText: 'Meaning' },
            { id: 2, exerciseId: 126, tagText: 'Life' },
            { id: 3, exerciseId: 126, tagText: 'Book' },
            { id: 4, exerciseId: 126, tagText: 'Culture' },
            { id: 5, exerciseId: 126, tagText: 'Philosophy' },
        ]
    }];

    test('displays the first exercise', () => {
        const { container, getByText } = render(
            <ExerciseList
                loading={false}
                language={languages[0]}
                exercises={sampleExercises}
            />,
            { wrapper: MemoryRouter }
        );
        expect(getByText(sampleExercises[0].questionBody)).toBeInTheDocument();
        expect(getByText(sampleExercises[0].optionA)).toBeInTheDocument();
        expect(getByText(sampleExercises[0].optionB)).toBeInTheDocument();
        expect(getByText(sampleExercises[0].optionC)).toBeInTheDocument();
        expect(getByText(sampleExercises[0].optionD)).toBeInTheDocument();
    });

    test('displays audio of the exercise', async () => {
        window.HTMLMediaElement.prototype.load = jest.fn();
        window.HTMLMediaElement.prototype.play = jest.fn();
        window.HTMLMediaElement.prototype.pause = jest.fn();
        window.HTMLMediaElement.prototype.addTextTrack = jest.fn();
        const { container, getByTestId } = render(
            <ExerciseList
                loading={false}
                language={languages[0]}
                exercises={sampleExercises.slice(1)}
            />,
            { wrapper: MemoryRouter }
        );

        expect(getByTestId("audio")).toBeInTheDocument();
        expect(getByTestId('audio-source').src)
            .toMatch(sampleExercises[1].soundUrl)
        expect(window.HTMLMediaElement.prototype.load).not
            .toHaveBeenCalled();
        expect(window.HTMLMediaElement.prototype.pause)
            .toHaveBeenCalled();
        jest.advanceTimersByTime(250);
        expect(window.HTMLMediaElement.prototype.load)
            .toHaveBeenCalled();
    });

    test('displays image of the exercise', () => {
        const { container, getByTestId } = render(
            <ExerciseList
                loading={false}
                language={languages[0]}
                exercises={sampleExercises.slice(2)}
            />,
            { wrapper: MemoryRouter }
        );

        expect(getByTestId('image')).toBeInTheDocument();
        expect(getByTestId('image').src)
            .toMatch(sampleExercises[2].imageUrl);
    });

    test('displays tags of the exercise', () => {
        const { container, getByTestId, getByText } = render(
            <ExerciseList
                loading={false}
                language={languages[0]}
                exercises={sampleExercises.slice(3)}
            />,
            { wrapper: MemoryRouter }
        );

        sampleExercises[3].tags.forEach(({ tagText }) => {
           expect(getByText(tagText)).toBeInTheDocument();
        });
    });
})