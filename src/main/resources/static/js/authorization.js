let token = sessionStorage.getItem('tokenData')
console.log(token)
let bearer = 'Bearer ' + token;

fetch('/api/auth/validate', {
    method: 'GET',
    credentials: 'include',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': bearer
    }
})
    .then(resp => resp.text().then(role => console.log(role)))