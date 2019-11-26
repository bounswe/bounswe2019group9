import { Form, Icon, Input, Button, Select, Row } from 'antd';
import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";

import {ExercisesHelper, GradesHelper, LanguagesHelper} from '../../../../../Helpers';

const { Option } = Select;

const Search = ({ onSubmit, form }) => {
  const handleSubmit = e => {
    e.preventDefault();
    form.validateFields((err, values) => {
      if (!err) {
        onSubmit(values);
      }
    })
  }

  const { getFieldDecorator, getFieldsError, getFieldError, isFieldTouched } = form;

  return (
    <Form layout="inline" onSubmit={handleSubmit}>
      <Form.Item>
        {getFieldDecorator('tag', { initialValue: "" })(
          <Input placeholder="Any" label="Tag" />
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('grade', { initialValue: 0 })(
          <Select
            title="Grade"
            style={{width: 120}}
          >
            <Option value={0}>All Grades</Option>
            { GradesHelper.grades.map(({ num_grade, str_grade}) => (
              <Option value={num_grade}>{str_grade}</Option>
            ))}
          </Select>
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('typeId', { initialValue: 0 })(
          <Select
            title="Type"
            style={{width: 120}}
          >
            <Option value={0}>All Types</Option>
            { ExercisesHelper.exerciseTypes.map(({ typeId, name }) => (
              <Option value={typeId}>{name}</Option>
            ))}
          </Select>
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('languageId', { initialValue: '' })(
          <Select
            label="Language"
            style={{width: 160}}
          >
            <Option value=''>All Languages</Option>
            { LanguagesHelper.languages.map(({ languageId, name }) => (
              <Option value={languageId}>{name}</Option>
            ))}
          </Select>
        )}
      </Form.Item>
      <Button type="primary" htmlType="submit">
        Search
      </Button>
    </Form>
  );
}

export default Form.create({name: 'inline_login'})(Search);
