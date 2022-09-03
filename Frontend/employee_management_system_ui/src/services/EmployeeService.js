import axios from "axios";

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/api/v1/employee"

class EmployeeService {

    saveEmployee(employee) {
        return axios.post(EMPLOYEE_API_BASE_URL + "/add", employee);
    }

    getEmployees() {
        return axios.get(EMPLOYEE_API_BASE_URL + "/getall");

    }

    deleteEmployee(id) {
        return axios.delete(EMPLOYEE_API_BASE_URL + "/delete" + "/" + id);

    }
}

export default new EmployeeService();