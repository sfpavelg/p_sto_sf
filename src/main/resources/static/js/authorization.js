const keyName = 'kataToken';

const token = getTokenFromCookie(keyName)

checkToken(token);


function checkToken(token) {
    if (token === null) {
        showError403()
    }
    const bearerToken = 'Bearer ' + token
    validate(bearerToken);
}

function getTokenFromCookie(key) {
    const str = document.cookie.split(';').find(x => x.includes(key));
    if (str === undefined) return null;
    return str.slice(str.indexOf('=') + 1).trim();
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