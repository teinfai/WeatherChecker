import React, { useState, useRef } from 'react';
import { Modal, Button, Form } from 'react-bootstrap';
import axios from 'axios';
import { toast } from 'react-toastify';

function RegisterModal({ show, handleClose }) {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        role: " " // default to Admin
    });

    const [validated, setValidated] = useState(false);
    const formRef = useRef(null);

    // console.log(formData);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleRegister = async () => {

        const form = formRef.current;

        if (!form.checkValidity()) {
            setValidated(true); // triggers red borders + error messages
            return;
        }
        try {
            console.log(formData);
            await axios.post('http://localhost:5000/api/users/register', formData);

            toast.success('✅ Registration successful');
            handleClose();
        } catch (err) {
            // console.log(err);
            toast.error('❌ Registration failed');
        }
    };

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Register</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form ref={formRef} noValidate validated={validated}>
                    <Form.Group className="mb-3">
                        <Form.Label>Username</Form.Label>
                        <Form.Control
                            required
                            name="name"
                            value={formData.name}
                            onChange={handleChange}
                        />
                        <Form.Control.Feedback type="invalid">
                            Please enter a username.
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            required
                            type="email"
                            name="email"
                            value={formData.email}
                            onChange={handleChange}
                        />
                        <Form.Control.Feedback type="invalid">
                            Please enter a valid email.
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            required
                            type="password"
                            name="password"
                            minLength={6}
                            value={formData.password}
                            onChange={handleChange}
                        />
                        <Form.Control.Feedback type="invalid">
                            Password must be at least 6 characters.
                        </Form.Control.Feedback>
                    </Form.Group>
                    <Form.Group className="mb-3">
                        <Form.Label>Role</Form.Label>
                        <Form.Select
                            required
                            name="role"
                            value={formData.role}
                            onChange={handleChange}
                        >
                            <option value="">Select Role</option>
                            <option value="1">Admin</option>
                            <option value="2">Manager</option>
                            <option value="3">User</option>
                            <option value="4">Guest</option>
                        </Form.Select>
                        <Form.Control.Feedback type="invalid">
                            Please select a role.
                        </Form.Control.Feedback>
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>Cancel</Button>
                <Button variant="primary" onClick={handleRegister}>Register</Button>
            </Modal.Footer>
        </Modal>
    );
}

export default RegisterModal;
