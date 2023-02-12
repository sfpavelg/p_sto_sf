app.post("/login", async (req, res) => {

    try {
         const {email, password} = req.body;

    if (!(email && password)) {
        res.status(400).send("Требуются все входные данные");
    }

    const user = await User.findOne({email});

    if (user && (await bcrypt.compare(password, user.password))) {
        const token = jwt.sign(
            {user_id: user._id, email},
            process.env.TOKEN_KEY,
            {
                expiresIn: "2h",
            }
        );

        res.cookie('token', token, { httpOnly: true });

        user.token = token;

        res.status(200).json(user);
    }
    res.status(400).send("Недействительные учетные данные");
    } catch (err) {
    console.log(err);
    }
    location.href = "/main";
});

