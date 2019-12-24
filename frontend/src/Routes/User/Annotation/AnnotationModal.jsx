import React, {useEffect, useReducer, useState} from 'react';
import { Modal, Form, Input, Select } from "antd";
import AnnotationData from "./AnnotationData/AnnotationData";

const AnnotationModal = ({ annotation, form, onClose }) => {
    const [state, dispatch] = useReducer((prevState, action) => {
        switch (action.type) {
            case 'reinitialize':
                return {
                    message: action.data.body,
                    motivation: action.data.motivation,
                    loading: false,
                };
            case 'loading':
                return {
                    loading: true
                };
            case 'loaded':
                return {
                    loading: false
                };
            default:
                return prevState;
        }
    }, { message: '', motivation: AnnotationData.motivationTypes[0], loading: false });


    useEffect(() => {
        dispatch({ type: 'reinitialize', data: annotation });
        form.resetFields();
    }, [annotation, form]);

    const handleSubmit = (e) => {
        e.preventDefault();
        form.validateFields((err, values) => {
            if (err) {
                return;
            }
            const { message, motivation } = values;
            const nextAnnotation = annotation.clone();
            nextAnnotation.setMessage(message);
            nextAnnotation.setMotivation(motivation);
            dispatch({ type: 'loading' });
            // call backend
            (new Promise((resolve) => setTimeout(resolve, 1000)))
            .then(() => {
                onClose(nextAnnotation);
            }).catch((err) => {

            }).finally(() => {
                dispatch({ type: 'loaded' });
            })
        })
    };

    const handleDelete = () => {

    };

    const handleCancel = (e) => {

    };

    return (

      <Modal
          visible={!!annotation}
          okButtonProps={{
              loading: state.loading,
              disabled: state.loading
          }}
          cancelButtonProps={{
              disabled: state.loading
          }}
      >
        <Form layout='vertical' onSubmit={handleSubmit}>
            <Form.Item label="Motivation">
                {form.getFieldDecorator('motivation', {
                    initialValue: state.motivation,
                    rules: [
                        { required: true, message: 'Please select a motivation' }
                    ]
                })(
                    <Select>
                        {
                            AnnotationData.motivationTypes
                                .map(({ label, value }) => (
                              <Select.Option value={value} key={value}>
                                  {label}
                              </Select.Option>
                            ))
                        }
                    </Select>
                )}
            </Form.Item>
            <Form.Item label="Message">
                {form.getFieldDecorator('message', {
                    initialValue: state.message,
                    rules: [
                        { required: true, message: 'Please enter a message' }
                    ]
                })(
                    <Input
                        placeholder="Enter your message"
                        type="textarea"
                    />
                )}
            </Form.Item>
        </Form>
      </Modal>
    );
};

export default Form.create({ name: 'annotation_form' })(AnnotationModal);