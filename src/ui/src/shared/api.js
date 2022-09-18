const BASE_URL = 'http://localhost:8080';

export const getEmployeeByPID = (empPID) => {
    return fetch(`${BASE_URL}/employee/M12348`)
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
    return fetch(`${BASE_URL}/department?pid=M12348`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const getLayout = (empPID) => {
    return fetch(`${BASE_URL}/layout?buildingId=1&pid=M12348`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const getAllocate = (empPID) => {
    return fetch(`${BASE_URL}/space/allocated?pid=M12348`)
        .then(async (response) => {
            return await response.json();
        })
        .catch((error) => {
            return Promise.reject(error);
        });
}

export const allocate = (data) => {
    console.log(data);
    return fetch(`${BASE_URL}/space/allocate?pid=M12348`, {
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