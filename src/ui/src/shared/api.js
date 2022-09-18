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

export const getEmployeeByOE = (empPID) => {
    return fetch(`${BASE_URL}/employee/?oeCodeId=3`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}


export const getDepartments = (empPID) => {
    return fetch(`${BASE_URL}/department?pid=${empPID}`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const getLayout = (empPID) => {
    return fetch(`${BASE_URL}/layout?buildingId=1&pid=${empPID}`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const getAllocate = (empPID) => {
    return fetch(`${BASE_URL}/space/allocated?pid=${empPID}`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const allocate = (empPID, data) => {
    return fetch(`${BASE_URL}/space/allocate?pid=${empPID}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
            // 'Content-Type': 'application/x-www-form-urlencoded',
          },
        body: JSON.stringify(data),
    })
    .then(async (response) => {
        return await response.json();
    })
    .catch((error) => {
        return Promise.reject(error);
    });
}