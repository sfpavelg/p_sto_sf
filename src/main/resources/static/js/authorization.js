const keyName = 'kataToken';

const token = localStorage.getItem(keyName)
if (token === null) {
    showError403();
}

const bearerToken = 'Bearer ' + token;
validate(bearerToken);


function validate(bearerToken) {
    fetch('/api/auth/validate', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': bearerToken
        }
    })
        .then(resp => {
            if (resp.status === 403) {
                showError403();
            }
        })
}

function showError403() {
    location.href = '/403';
}