/**
 * @fileoverview
 * @enhanceable
 * @public
 */
// GENERATED CODE -- DO NOT EDIT!

var jspb = require('google-protobuf');
var goog = jspb;
var global = Function('return this')();

goog.exportSymbol('proto.fun.dodo.tools.meta.Demo', null, global);
goog.exportSymbol('proto.fun.dodo.tools.meta.DemoList', null, global);

/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.fun.dodo.tools.meta.Demo = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, null, null);
};
goog.inherits(proto.fun.dodo.tools.meta.Demo, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  proto.fun.dodo.tools.meta.Demo.displayName = 'proto.fun.dodo.tools.meta.Demo';
}


if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto suitable for use in Soy templates.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     com.google.apps.jspb.JsClassTemplate.JS_RESERVED_WORDS.
 * @param {boolean=} opt_includeInstance Whether to include the JSPB instance
 *     for transitional soy proto support: http://goto/soy-param-migration
 * @return {!Object}
 */
proto.fun.dodo.tools.meta.Demo.prototype.toObject = function(opt_includeInstance) {
  return proto.fun.dodo.tools.meta.Demo.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Whether to include the JSPB
 *     instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.fun.dodo.tools.meta.Demo} msg The msg instance to transform.
 * @return {!Object}
 */
proto.fun.dodo.tools.meta.Demo.toObject = function(includeInstance, msg) {
  var f, obj = {
    id: jspb.Message.getFieldWithDefault(msg, 1, 0),
    ownerId: jspb.Message.getFieldWithDefault(msg, 2, 0),
    type: jspb.Message.getFieldWithDefault(msg, 3, 0),
    name: jspb.Message.getFieldWithDefault(msg, 4, ""),
    notes: jspb.Message.getFieldWithDefault(msg, 5, ""),
    enabled: jspb.Message.getFieldWithDefault(msg, 21, false),
    createdAt: jspb.Message.getFieldWithDefault(msg, 22, 0),
    updatedAt: jspb.Message.getFieldWithDefault(msg, 23, 0)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.fun.dodo.tools.meta.Demo}
 */
proto.fun.dodo.tools.meta.Demo.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.fun.dodo.tools.meta.Demo;
  return proto.fun.dodo.tools.meta.Demo.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.fun.dodo.tools.meta.Demo} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.fun.dodo.tools.meta.Demo}
 */
proto.fun.dodo.tools.meta.Demo.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setId(value);
      break;
    case 2:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setOwnerId(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readUint32());
      msg.setType(value);
      break;
    case 4:
      var value = /** @type {string} */ (reader.readString());
      msg.setName(value);
      break;
    case 5:
      var value = /** @type {string} */ (reader.readString());
      msg.setNotes(value);
      break;
    case 21:
      var value = /** @type {boolean} */ (reader.readBool());
      msg.setEnabled(value);
      break;
    case 22:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setCreatedAt(value);
      break;
    case 23:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setUpdatedAt(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.fun.dodo.tools.meta.Demo.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.fun.dodo.tools.meta.Demo.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.fun.dodo.tools.meta.Demo} message
 * @param {!jspb.BinaryWriter} writer
 */
proto.fun.dodo.tools.meta.Demo.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getId();
  if (f !== 0) {
    writer.writeUint64(
      1,
      f
    );
  }
  f = message.getOwnerId();
  if (f !== 0) {
    writer.writeUint64(
      2,
      f
    );
  }
  f = message.getType();
  if (f !== 0) {
    writer.writeUint32(
      3,
      f
    );
  }
  f = message.getName();
  if (f.length > 0) {
    writer.writeString(
      4,
      f
    );
  }
  f = message.getNotes();
  if (f.length > 0) {
    writer.writeString(
      5,
      f
    );
  }
  f = message.getEnabled();
  if (f) {
    writer.writeBool(
      21,
      f
    );
  }
  f = message.getCreatedAt();
  if (f !== 0) {
    writer.writeUint64(
      22,
      f
    );
  }
  f = message.getUpdatedAt();
  if (f !== 0) {
    writer.writeUint64(
      23,
      f
    );
  }
};


/**
 * optional uint64 id = 1;
 * @return {number}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getId = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 1, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.Demo.prototype.setId = function(value) {
  jspb.Message.setField(this, 1, value);
};


/**
 * optional uint64 owner_id = 2;
 * @return {number}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getOwnerId = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 2, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.Demo.prototype.setOwnerId = function(value) {
  jspb.Message.setField(this, 2, value);
};


/**
 * optional uint32 type = 3;
 * @return {number}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getType = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.Demo.prototype.setType = function(value) {
  jspb.Message.setField(this, 3, value);
};


/**
 * optional string name = 4;
 * @return {string}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getName = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 4, ""));
};


/** @param {string} value */
proto.fun.dodo.tools.meta.Demo.prototype.setName = function(value) {
  jspb.Message.setField(this, 4, value);
};


/**
 * optional string notes = 5;
 * @return {string}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getNotes = function() {
  return /** @type {string} */ (jspb.Message.getFieldWithDefault(this, 5, ""));
};


/** @param {string} value */
proto.fun.dodo.tools.meta.Demo.prototype.setNotes = function(value) {
  jspb.Message.setField(this, 5, value);
};


/**
 * optional bool enabled = 21;
 * Note that Boolean fields may be set to 0/1 when serialized from a Java server.
 * You should avoid comparisons like {@code val === true/false} in those cases.
 * @return {boolean}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getEnabled = function() {
  return /** @type {boolean} */ (jspb.Message.getFieldWithDefault(this, 21, false));
};


/** @param {boolean} value */
proto.fun.dodo.tools.meta.Demo.prototype.setEnabled = function(value) {
  jspb.Message.setField(this, 21, value);
};


/**
 * optional uint64 created_at = 22;
 * @return {number}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getCreatedAt = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 22, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.Demo.prototype.setCreatedAt = function(value) {
  jspb.Message.setField(this, 22, value);
};


/**
 * optional uint64 updated_at = 23;
 * @return {number}
 */
proto.fun.dodo.tools.meta.Demo.prototype.getUpdatedAt = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 23, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.Demo.prototype.setUpdatedAt = function(value) {
  jspb.Message.setField(this, 23, value);
};



/**
 * Generated by JsPbCodeGenerator.
 * @param {Array=} opt_data Optional initial data array, typically from a
 * server response, or constructed directly in Javascript. The array is used
 * in place and becomes part of the constructed object. It is not cloned.
 * If no data is provided, the constructed object will be empty, but still
 * valid.
 * @extends {jspb.Message}
 * @constructor
 */
proto.fun.dodo.tools.meta.DemoList = function(opt_data) {
  jspb.Message.initialize(this, opt_data, 0, -1, proto.fun.dodo.tools.meta.DemoList.repeatedFields_, null);
};
goog.inherits(proto.fun.dodo.tools.meta.DemoList, jspb.Message);
if (goog.DEBUG && !COMPILED) {
  proto.fun.dodo.tools.meta.DemoList.displayName = 'proto.fun.dodo.tools.meta.DemoList';
}
/**
 * List of repeated fields within this message type.
 * @private {!Array<number>}
 * @const
 */
proto.fun.dodo.tools.meta.DemoList.repeatedFields_ = [1];



if (jspb.Message.GENERATE_TO_OBJECT) {
/**
 * Creates an object representation of this proto suitable for use in Soy templates.
 * Field names that are reserved in JavaScript and will be renamed to pb_name.
 * To access a reserved field use, foo.pb_<name>, eg, foo.pb_default.
 * For the list of reserved names please see:
 *     com.google.apps.jspb.JsClassTemplate.JS_RESERVED_WORDS.
 * @param {boolean=} opt_includeInstance Whether to include the JSPB instance
 *     for transitional soy proto support: http://goto/soy-param-migration
 * @return {!Object}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.toObject = function(opt_includeInstance) {
  return proto.fun.dodo.tools.meta.DemoList.toObject(opt_includeInstance, this);
};


/**
 * Static version of the {@see toObject} method.
 * @param {boolean|undefined} includeInstance Whether to include the JSPB
 *     instance for transitional soy proto support:
 *     http://goto/soy-param-migration
 * @param {!proto.fun.dodo.tools.meta.DemoList} msg The msg instance to transform.
 * @return {!Object}
 */
proto.fun.dodo.tools.meta.DemoList.toObject = function(includeInstance, msg) {
  var f, obj = {
    demoList: jspb.Message.toObjectList(msg.getDemoList(),
    proto.fun.dodo.tools.meta.Demo.toObject, includeInstance),
    count: jspb.Message.getFieldWithDefault(msg, 2, 0),
    index: jspb.Message.getFieldWithDefault(msg, 3, 0),
    size: jspb.Message.getFieldWithDefault(msg, 4, 0)
  };

  if (includeInstance) {
    obj.$jspbMessageInstance = msg;
  }
  return obj;
};
}


/**
 * Deserializes binary data (in protobuf wire format).
 * @param {jspb.ByteSource} bytes The bytes to deserialize.
 * @return {!proto.fun.dodo.tools.meta.DemoList}
 */
proto.fun.dodo.tools.meta.DemoList.deserializeBinary = function(bytes) {
  var reader = new jspb.BinaryReader(bytes);
  var msg = new proto.fun.dodo.tools.meta.DemoList;
  return proto.fun.dodo.tools.meta.DemoList.deserializeBinaryFromReader(msg, reader);
};


/**
 * Deserializes binary data (in protobuf wire format) from the
 * given reader into the given message object.
 * @param {!proto.fun.dodo.tools.meta.DemoList} msg The message object to deserialize into.
 * @param {!jspb.BinaryReader} reader The BinaryReader to use.
 * @return {!proto.fun.dodo.tools.meta.DemoList}
 */
proto.fun.dodo.tools.meta.DemoList.deserializeBinaryFromReader = function(msg, reader) {
  while (reader.nextField()) {
    if (reader.isEndGroup()) {
      break;
    }
    var field = reader.getFieldNumber();
    switch (field) {
    case 1:
      var value = new proto.fun.dodo.tools.meta.Demo;
      reader.readMessage(value,proto.fun.dodo.tools.meta.Demo.deserializeBinaryFromReader);
      msg.addDemo(value);
      break;
    case 2:
      var value = /** @type {number} */ (reader.readUint32());
      msg.setCount(value);
      break;
    case 3:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setIndex(value);
      break;
    case 4:
      var value = /** @type {number} */ (reader.readUint64());
      msg.setSize(value);
      break;
    default:
      reader.skipField();
      break;
    }
  }
  return msg;
};


/**
 * Serializes the message to binary data (in protobuf wire format).
 * @return {!Uint8Array}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.serializeBinary = function() {
  var writer = new jspb.BinaryWriter();
  proto.fun.dodo.tools.meta.DemoList.serializeBinaryToWriter(this, writer);
  return writer.getResultBuffer();
};


/**
 * Serializes the given message to binary data (in protobuf wire
 * format), writing to the given BinaryWriter.
 * @param {!proto.fun.dodo.tools.meta.DemoList} message
 * @param {!jspb.BinaryWriter} writer
 */
proto.fun.dodo.tools.meta.DemoList.serializeBinaryToWriter = function(message, writer) {
  var f = undefined;
  f = message.getDemoList();
  if (f.length > 0) {
    writer.writeRepeatedMessage(
      1,
      f,
      proto.fun.dodo.tools.meta.Demo.serializeBinaryToWriter
    );
  }
  f = message.getCount();
  if (f !== 0) {
    writer.writeUint32(
      2,
      f
    );
  }
  f = message.getIndex();
  if (f !== 0) {
    writer.writeUint64(
      3,
      f
    );
  }
  f = message.getSize();
  if (f !== 0) {
    writer.writeUint64(
      4,
      f
    );
  }
};


/**
 * repeated Demo demo = 1;
 * If you change this array by adding, removing or replacing elements, or if you
 * replace the array itself, then you must call the setter to update it.
 * @return {!Array.<!proto.fun.dodo.tools.meta.Demo>}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.getDemoList = function() {
  return /** @type{!Array.<!proto.fun.dodo.tools.meta.Demo>} */ (
    jspb.Message.getRepeatedWrapperField(this, proto.fun.dodo.tools.meta.Demo, 1));
};


/** @param {!Array.<!proto.fun.dodo.tools.meta.Demo>} value */
proto.fun.dodo.tools.meta.DemoList.prototype.setDemoList = function(value) {
  jspb.Message.setRepeatedWrapperField(this, 1, value);
};


/**
 * @param {!proto.fun.dodo.tools.meta.Demo=} opt_value
 * @param {number=} opt_index
 * @return {!proto.fun.dodo.tools.meta.Demo}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.addDemo = function(opt_value, opt_index) {
  return jspb.Message.addToRepeatedWrapperField(this, 1, opt_value, proto.fun.dodo.tools.meta.Demo, opt_index);
};


proto.fun.dodo.tools.meta.DemoList.prototype.clearDemoList = function() {
  this.setDemoList([]);
};


/**
 * optional uint32 count = 2;
 * @return {number}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.getCount = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 2, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.DemoList.prototype.setCount = function(value) {
  jspb.Message.setField(this, 2, value);
};


/**
 * optional uint64 index = 3;
 * @return {number}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.getIndex = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 3, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.DemoList.prototype.setIndex = function(value) {
  jspb.Message.setField(this, 3, value);
};


/**
 * optional uint64 size = 4;
 * @return {number}
 */
proto.fun.dodo.tools.meta.DemoList.prototype.getSize = function() {
  return /** @type {number} */ (jspb.Message.getFieldWithDefault(this, 4, 0));
};


/** @param {number} value */
proto.fun.dodo.tools.meta.DemoList.prototype.setSize = function(value) {
  jspb.Message.setField(this, 4, value);
};


goog.object.extend(exports, proto.fun.dodo.tools.meta);
