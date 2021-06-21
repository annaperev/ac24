FROM adoptopenjdk/maven-openjdk11
COPY . /sources
ENTRYPOINT ["/sources/build_and_test_and_run.sh"]
