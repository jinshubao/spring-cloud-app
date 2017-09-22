let merge = require('webpack-merge');
let prodEnv = require('./prod.env');

module.exports = merge(prodEnv, {
    NODE_ENV: '"development"',
    API_URL : '"http://10.52.2.5:8081"'
});
