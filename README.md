# Cycle Saver


A simple web app to calculate the savings made by commuting via bike. Please note, this is powered by Transport for London and as such will only work within London.

Visit the website at: [https://cycle-saver.herokuapp.com](https://cycle-saver.herokuapp.com/)

Or Connect your stava account now:\
[![ ](https://s3-eu-west-1.amazonaws.com/horscatweb/LogInWithStrava.png)](https://www.strava.com/oauth/authorize?client_id=28817&response_type=code&redirect_uri=http://localhost:5000%2Fauth%2Fstrava&approval_prompt=force)

## Running Locally

Ensure Java, Maven and the [Heroku CLI](https://cli.heroku.com/) are installed.

```sh
$ git clone git@github.com:Michael-Wheeler/cycle-saver.git
$ cd cycle-saver
$ mvn clean install
$ heroku local web
```

Your app should now be running on [localhost:5000](http://localhost:5000/).