# mongoex

A very simple example illustrating MongoDB with Clojure


## Usage

Needs to import the document in resources/user.json first

    $ mongoimport -d "tests-db" -c "users" resources/user.json

Tests

    $ lein midje
    
Continual testing (run tests everytime source changes)

    $ lein autotest

Running

    $ lein run


## Examples

After being imported and the server is running:

https://localhost:1234/users

### Bugs

...

## License

Copyright © 2015

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
