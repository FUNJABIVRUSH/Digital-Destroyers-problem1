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


export const getDepartments = (empPID) => {
    return fetch(`${BASE_URL}/department?pid=M12345`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const getLayout = (empPID) => {
    return fetch(`${BASE_URL}/layout?buildingId=1&pid=M12345`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

