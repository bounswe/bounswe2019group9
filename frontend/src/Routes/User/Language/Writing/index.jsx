import React from 'react';
import {Form, Input, Button, PageHeader, Select, Switch, Upload, message, Icon } from 'antd';
import {addWriting, getAssignmentsById, uploadEssayPhoto} from "../../../../Api/Writing";
import { useParams, withRouter } from "react-router-dom";
import {LanguagesHelper} from "../../../../Helpers";
import {nameToLanguage} from "../../../../Helpers/languages";
import { connect } from "../../../../Store";
import {toast} from "react-toastify";

function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
}

function beforeUpload(file) {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
    if (!isJpgOrPng) {
        message.error('You can only upload JPG/PNG file!');
    }
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
        message.error('Image must smaller than 2MB!');
    }
    return isJpgOrPng && isLt2M;
}

class Writing extends React.Component {
    constructor(props) {
        super(props);
        const { language } = props.match.params;
        this.userId = this.props.store.userId;
        this.languageId = nameToLanguage(language).languageId;
        this.state = {
           subject: "",
            text: "",
            submitted: false,
            photo: false,
            loading: false,
            chosenAssignment: null,
            assignments: []
        };
    }

    componentDidMount() {
        getAssignmentsById(this.languageId).then((res) => {
            const { data = [] } = res.data || {};
            this.setState({
                assignments: data
            });
        })
    }

    onChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    submit = (e)=> {
        e.preventDefault();
        const { photo, photoUrl } = this.state;
        this.props.form.validateFields((err, values) => {
            if (err) {
                this.setState({ error: err.message });
                return;
            }
            addWriting({
                authorId: this.userId,
                assignmentId: values.assignmentId,
                sourceType: photo ? 2 : 1,
                source: photo ? photoUrl : values.body
            }).then(()=> {
                toast.success('You have successfully submitted essay');
                this.props.history.push('/essays');
            });
        })

    }

    handleUploadChange = info => {
        if (info.file.status === 'uploading') {
            this.setState({ loading: true });
            return;
        }
        if (info.file.status === 'done') {
            // Get this url from response in real world.
            getBase64(info.file.originFileObj, imageUrl => {
                this.setState({
                    imageUrl,
                    loading: false,
                });
                uploadEssayPhoto({
                    authorId: this.userId,
                    base64Data: imageUrl,
                    exerciseId: this.state.chosenAssignment
                }).then(res => {
                    const { data } = res.data || {};
                    this.setState({
                        photoUrl: data
                    });
                }).catch(console.log);
                // upload image maybe
            });
        }
    };
    renderUpload = () => {
        const uploadButton = (
            <div>
                <Icon type={this.state.loading ? 'loading' : 'plus'} />
                <div className="ant-upload-text">Upload</div>
            </div>
        );
        const { imageUrl } = this.state;
        return (
            <Upload
                name="avatar"
                listType="picture-card"
                className="avatar-uploader"
                showUploadList={false}
                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                beforeUpload={beforeUpload}
                onChange={this.handleUploadChange}
            >
                {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
            </Upload>
        );
    };

    render() {
        const { form } = this.props;
        const { photo, subject, text, submitted, assignments } = this.state;
        return (
            !submitted ? (<div style={{ overflow: 'auto', height: '100%' }}>
                <PageHeader
                    title="Writing Exercise"
                />
                <Form layout="vertical" onSubmit={this.submit}>
                    <Form.Item label="Subject" >
                        {
                            form.getFieldDecorator('assignmentId', {
                                rules: [{required: true, message: 'Please select a assignmentId'}]
                            })(
                                <Select placeholder="Please choose a subject from here" onChange={(val) => this.setState({ chosenAssignment: val })}>
                                    { assignments.map(({ id, question }) => (
                                        <Select.Option key={id} value={id}>
                                            { question }
                                        </Select.Option>
                                    ))}
                                </Select>
                            )
                        }
                    </Form.Item>
                    Text <Switch onChange={(photo) => this.setState({ photo })}/> Photo
                    <br /> <br/>
                    {
                        photo ? (
                            this.renderUpload()
                        ) : (
                            <Form.Item label="Essay">
                                {
                                    form.getFieldDecorator('body', {
                                        rules: [{required: true, message: 'Please write your essay'}]
                                    })(
                                        <Input.TextArea rows={30} placeholder="Write your essay here" />
                                    )
                                }

                            </Form.Item>
                        )
                    }
                    <Form.Item >
                        <Button htmlType="submit" type="primary">Submit</Button>
                    </Form.Item>
                </Form>
            </div>) : (
                <div>
                    <PageHeader
                        title="Writing Exercise"
                    />
                    <span>You have successfully submitted your writing exercise!</span>
                </div>
            )
        );
    }
}

export default connect(withRouter(Form.create({name: 'writing_form'})(Writing)));