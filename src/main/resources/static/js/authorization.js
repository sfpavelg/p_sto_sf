const keyName = 'kataToken';

const tokenJson = localStorage.getItem(keyName)
const token = JSON.parse(tokenJson)

if (isTokenChecked(token)) {
    const bearerToken = 'Bearer ' + token.value;
    validate(bearerToken);
} else {
    showError403();
}


function isTokenChecked(token) {
    if (token === null) return false;

    if (new Date() > new Date(token.expTime)) {
        localStorage.removeItem(keyName);
        return false;
    }
    return true;
}

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