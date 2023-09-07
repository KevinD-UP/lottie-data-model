'use strict';

if (process.env.NODE_ENV === 'production') {
  module.exports = require('./animation-transformer.production.js');
} else {
  module.exports = require('./animation-transformer.development.js');
}