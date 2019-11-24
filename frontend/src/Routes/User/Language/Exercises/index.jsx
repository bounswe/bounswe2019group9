import React from 'react';
import { useParams } from 'react-router-dom';
import { connect } from '../../../../Store';

const Exercises = ({ store }) => {
  const { userId } = store;
  const { language, exerciseType } = useParams();
  return (
    <div>
      Todo, add exercises here
      <div>
        { userId }
      </div>
      <div>
        { language }
      </div>
      <div>
        { exerciseType}
      </div>
    </div>
  )
};

export default connect(Exercises);
