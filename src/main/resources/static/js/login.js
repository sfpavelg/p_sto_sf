function saveToken(token) {
    sessionStorage.setItem('tokenData', JSON.stringify(token));
}

function getTokenData(email, password) {
    return fetch('api/auth', {
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
                saveToken(JSON.stringify(tokenData));
                return Promise.resolve()
            }
            return Promise.reject();
        });

}

location.href = "/main";