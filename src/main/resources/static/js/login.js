if (document.URL.endsWith('logout')) {
    logOut();
}

let loginButton = document.getElementById('loginButton');
let email = document.getElementById('email');
let password = document.getElementById('password');
loginButton.addEventListener('click', () => getTokenData(
    email.value,
    password.value
));

async function saveToken(tokenData) {
    const json = await tokenData.then(res => JSON.parse(JSON.stringify(res)));
    const token = json.token;
    localStorage.setItem('tokenData', JSON.stringify(token).replaceAll('\"', ''));
}

function getTokenData(email, password) {
    return fetch('api/auth/token', {
        method: 'POST',
        credentials: 'include',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email,
            password,
        }),
    })
        .then((res) => {
            if (res.status === 200) {
                const tokenData = res.json();
                saveToken(tokenData);
                location.href = '/users'
                return Promise.resolve()
            }
            alert('Неверный пароль');
            return Promise.reject();
        });

}

function logOut() {
    localStorage.clear()
}