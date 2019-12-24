import React, {useEffect, useReducer, useState} from 'react';
import { Modal, Form, Input, Select, Button, Popconfirm, Icon } from "antd";
import AnnotationData from "./AnnotationData/AnnotationData";

const AnnotationModal = ({ annotation, form, setEditingAnnotation, onSaveAnnotation, onRemoveAnnotation }) => {
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
        if (annotation) {
            dispatch({ type: 'reinitialize', data: annotation });
            form.resetFields();
        }
    }, [annotation]);

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
            onSaveAnnotation(annotation, nextAnnotation)
            .then(() => {
                setEditingAnnotation(null);
            }).catch((err) => {

            }).finally(() => {
                dispatch({ type: 'loaded' });
            })
        })
    };

    const handleDelete = (e) => {
        e.preventDefault();
        onRemoveAnnotation(annotation);

    };

    const handleCancel = (e) => {
        e.preventDefault();
        if (!state.loading) {
            if (!annotation.id) {
                onRemoveAnnotation(annotation);
            } else {
                setEditingAnnotation(null);
            }
        }
    };

    return (

      <Modal
          title="Annotate Essay"
          visible={!!annotation}
          onOk={handleSubmit}
          onCancel={handleCancel}
          footer={[
              <Popconfirm
                  key="delete"
                  title="Are you sure you want to delete this annotation？"
                  icon={<Icon type="question-circle-o" style={{ color: 'red' }} />}
                  onConfirm={handleDelete}
              >
                  <Button type="danger" disabled={state.loading}>
                      Delete
                  </Button>,
              </Popconfirm>,
              <Button key="back" onClick={handleCancel} disabled={state.loading}>
                  Close
              </Button>,
              <Button key="submit" onClick={handleSubmit} type="primary" loading={state.loading}>
                  Save
              </Button>
          ]}
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