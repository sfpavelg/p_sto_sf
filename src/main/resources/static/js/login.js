let loginButton = document.getElementById('loginButton');
let some;
let email = document.getElementById('email');
let password = document.getElementById('password');
loginButton.addEventListener('click', () => {
    some = getTokenData(
        email.value,
        password.value
    );
})

async function saveToken(tokenData) {
    const json = await tokenData.then(res => JSON.parse(JSON.stringify(res)));
    const token = json.token;
    console.log(token)
    sessionStorage.setItem('tokenData', JSON.stringify(token).replaceAll('\"',''));
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
            return Promise.reject();
        });

}

//location.href = "/main";