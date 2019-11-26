import { Form, Icon, Input, Button, Select, Row } from 'antd';
import React, { useEffect, useState } from 'react';
import { useParams } from "react-router-dom";

import { connect, storeType } from '../../../Store';
import { getProfileInfoByUserId } from '../../../Api/User';
import { numGradeToStrGrade } from '../../../Helpers/grades';
import { CenterView } from '../../../Layouts/index';

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
        {getFieldDecorator('firstName', { initialValue: "" })(
          <Input placeholder="All First Names" label="First Name" />
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('lastName', { initialValue: "" })(
          <Input placeholder="All Last Names" label="Last Name" />
        )}
      </Form.Item>
      <Form.Item>
        {getFieldDecorator('grade', { initialValue: 0 })(
          <Select
            title="Grade"
            style={{width: 120}}
          >
            <Option value={0}>All Grades</Option>
            <Option value={1}>A1</Option>
            <Option value={2}>A2</Option>
            <Option value={3}>B1</Option>
            <Option value={4}>B2</Option>
            <Option value={5}>C1</Option>
            <Option value={6}>C2</Option>
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
            <Option value='1'>English</Option>
            <Option value='2'>Turkish</Option>
            <Option value='3'>Italian</Option>
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