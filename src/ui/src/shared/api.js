const BASE_URL = 'http://localhost:8080';

export const getEmployeeByPID = (empPID) => {
    return fetch(`${BASE_URL}/employee/${empPID}`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}