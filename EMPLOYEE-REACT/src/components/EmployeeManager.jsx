import React, { useState, useEffect } from "react";
import axios from "axios";

import "./style.css";

const EmployeeManager = () => {
  const [employees, setEmployees] = useState([]);
  const [employee, setEmployee] = useState({
    id: "",
    firstName: "",
    lastName: "",
    email: ""
  });
  const [editMode, setEditMode] = useState(false);
  const [message, setMessage] = useState("");

  const baseUrl = `${import.meta.env.VITE_API_URL}/employeeapi`;

  useEffect(() => {
    fetchAllEmployees();
  }, []);

  const fetchAllEmployees = async () => {
    try {
      const res = await axios.get(`${baseUrl}/all`);
      setEmployees(res.data);
    } catch (error) {
      setMessage("Failed to fetch employees.");
    }
  };

  const handleChange = (e) => {
    setEmployee({ ...employee, [e.target.name]: e.target.value });
  };

  const addEmployee = async () => {
    try {
      await axios.post(`${baseUrl}/add`, employee);
      setMessage("Employee added successfully.");
      fetchAllEmployees();
      resetForm();
    } catch {
      setMessage("Error adding employee.");
    }
  };

  const updateEmployee = async () => {
    try {
      await axios.put(`${baseUrl}/update`, employee);
      setMessage("Employee updated successfully.");
      fetchAllEmployees();
      resetForm();
    } catch {
      setMessage("Error updating employee.");
    }
  };

  const deleteEmployee = async (id) => {
    try {
      const res = await axios.delete(`${baseUrl}/delete/${id}`);
      setMessage(res.data);
      fetchAllEmployees();
    } catch {
      setMessage("Error deleting employee.");
    }
  };

  const handleEdit = (emp) => {
    setEmployee(emp);
    setEditMode(true);
    setMessage(`Editing employee with ID ${emp.id}`);
  };

  const resetForm = () => {
    setEmployee({
      id: "",
      firstName: "",
      lastName: "",
      email: ""
    });
    setEditMode(false);
  };

  return (
    <div className="employee-container">
      {message && (
        <div
          className={`message-banner ${
            message.toLowerCase().includes("error") ? "error" : "success"
          }`}
        >
          {message}
        </div>
      )}

      <h2>Employee Management System</h2>

      <div>
        <h3>{editMode ? "Edit Employee" : "Add Employee"}</h3>
        <div className="form-grid">
          <input
            type="text"
            name="firstName"
            placeholder="First Name"
            value={employee.firstName}
            onChange={handleChange}
          />
          <input
            type="text"
            name="lastName"
            placeholder="Last Name"
            value={employee.lastName}
            onChange={handleChange}
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            value={employee.email}
            onChange={handleChange}
          />
        </div>

        <div className="btn-group">
          {!editMode ? (
            <button className="btn-blue" onClick={addEmployee}>
              Add Employee
            </button>
          ) : (
            <>
              <button className="btn-green" onClick={updateEmployee}>
                Update Employee
              </button>
              <button className="btn-gray" onClick={resetForm}>
                Cancel
              </button>
            </>
          )}
        </div>
      </div>

      <div>
        <h3>All Employees</h3>
        {employees.length === 0 ? (
          <p>No employees found.</p>
        ) : (
          <div className="table-wrapper">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {employees.map((emp) => (
                  <tr key={emp.id}>
                    <td>{emp.id}</td>
                    <td>{emp.firstName}</td>
                    <td>{emp.lastName}</td>
                    <td>{emp.email}</td>
                    <td>
                      <div className="action-buttons">
                        <button
                          className="btn-green"
                          onClick={() => handleEdit(emp)}
                        >
                          Edit
                        </button>
                        <button
                          className="btn-red"
                          onClick={() => deleteEmployee(emp.id)}
                        >
                          Delete
                        </button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </div>
  );
};

export default EmployeeManager;
