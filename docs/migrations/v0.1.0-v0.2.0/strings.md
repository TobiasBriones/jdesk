# Strings

I used the following code to migrate POSLANS strings to the new and better
implementation.

It requires an `strs` file with only the old int constants, formatted, and
separated by line from the `Strings. java` file, and the old
`StringResources` file to convert the values to the `string.properties` format.

The code and example files are left 
[here](https://github.com/tobiasbriones/jdesk/tree/main/docs/migrations/v0.1.0-v0.2.0).

This is an under-engineered implementation (shitty code) because I only needed a
tool to do the work only once to avoid doing it by hand which is way too much.
It doesn't make sense to over-engineer something like this, and it's a clear
example of avoiding over-engineering.
