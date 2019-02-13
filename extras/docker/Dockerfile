# syntax = docker/dockerfile:experimental

FROM ubuntu:bionic AS build-env

WORKDIR /
RUN --mount=target=/var/lib/apt/lists,type=cache \
    --mount=target=/var/cache/apt,type=cache \
    mkdir /src && \
    apt-get update && \
    apt-get dist-upgrade -yy && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
            build-essential sudo

ADD . /src/vpp
RUN --mount=target=/var/lib/apt/lists,type=cache \
    --mount=target=/var/cache/apt,type=cache \
    cd /src/vpp && \
    make UNATTENDED=yes install-dep && \
    make pkg-deb V=1

FROM ubuntu:bionic AS release
WORKDIR /
COPY --from=build-env /src/vpp/build-root/vpp_*.deb \
		      /src/vpp/build-root/vpp-plugin-core_*.deb \
		      /src/vpp/build-root/libvppinfra_*.deb \
		      /src/vpp/build-root/vpp-api-python_*.deb /tmp/
RUN --mount=target=/var/lib/apt/lists,type=cache \
    --mount=target=/var/cache/apt,type=cache \
    apt-get update && apt-get dist-upgrade -yy && \
    apt-get install --no-install-recommends -yy /tmp/*.deb && \
    find /tmp -name "*.deb" -delete
ENTRYPOINT /usr/bin/vpp

FROM release AS debug
WORKDIR /
COPY --from=build-env /src/vpp/build-root/vpp-dev_*.deb \
		      /src/vpp/build-root/vpp-dbg_*.deb \
		      /src/vpp/build-root/libvppinfra-dev_*.deb /tmp/
RUN --mount=target=/var/lib/apt/lists,type=cache \
    --mount=target=/var/cache/apt,type=cache \
    apt-get update && apt-get dist-upgrade -yy && \
    apt-get install --no-install-recommends -yy gdb strace /tmp/*.deb && \
    find /tmp -name "*.deb" -delete
ENTRYPOINT /bin/bash