exports.handler = async (event) => {
    console.log('***** Incoming event:', event);
    if (event.identitySource) {

        const token = event.identitySource[0].replace(/Basic /g, '');
        const decoded = Buffer.from(token, 'base64').toString('utf8');
        const credentials = decoded.split(':');

        const isAuthorized = ("admin" === credentials[0] && "secret" === credentials[1]);
        return { isAuthorized };

    } else {
        throw Error("missing identitySource, event: " + JSON.stringify(event));
    }
};