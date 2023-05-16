package lottieAnimation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement
import lottieAnimation.layer.KPLayer
import lottieAnimation.layer.serializers.KPLayerListSerializer

/**
 * Top level object, describing the animation
 *
 *
 *
 * Base class for layer holders
 */
@Serializable
data class KPLottieAnimation(
    /**
     * Match name, used in expressions
     */
    var mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    var nm: String? = null,

    /**
     * List of assets that can be referenced by layers
     */
    var assets: List<Asset>? = null,

    /**
     * Data defining text characters as lottie shapes. If present a player might only render
     * characters defined here and nothing else.
     */
    var chars: List<CharacterData>? = null,

    /**
     * List of Extra compositions not referenced by anything
     */
    var comps: List<Precomposition>? = null,

    /**
     * Whether the animation has 3D layers
     */
    var ddd: Long? = null,

    var fonts: FontList? = null,

    /**
     * Framerate in frames per second
     */
    var fr: JsonElement,

    /**
     * Height of the animation
     */
    var h: Long,

    /**
     * "In Point", which frame the animation starts at (usually 0)
     */
    var ip: JsonElement,

    /**
     * Markers defining named sections of the composition.
     */
    var markers: List<Marker>? = null,

    var mb: MotionBlur? = null,

    /**
     * Document metadata
     */
    var meta: Metadata? = null,

    var metadata: UserMetadata? = null,

    /**
     * "Out Point", which frame the animation stops/loops at, which makes this the duration in
     * frames when `ip` is 0
     */
    var op: JsonElement,

    var v: String? = null,

    /**
     * Width of the animation
     */
    var w: Long,

    @Serializable(with = KPLayerListSerializer::class)
    var layers: List<KPLayer>
)

/**
 * External image
 *
 * Asset referencing a file
 *
 *
 *
 * Asset containing an animation that can be referenced by layers.
 *
 * Base class for layer holders
 *
 * External sound
 *
 * External data source, usually a JSON file
 */
@Serializable
data class Asset(
    /**
     * Unique identifier used by layers when referencing this asset
     */
    var id: String,

    /**
     * Human readable name
     */
    var nm: String? = null,

    /**
     * Whether the file is embedded
     */
    var e: Long? = null,

    /**
     * Filename or data url
     */
    var p: String? = null,

    /**
     * Path to the directory containing a file
     */
    var u: String? = null,

    /**
     * Height of the image
     */
    var h: Int? = null,

    /**
     * Marks as part of an image sequence if present
     */
    var t: JsonElement? = null,

    /**
     * Width of the image
     */
    var w: Int? = null,

    @Serializable(with = KPLayerListSerializer::class)
    var layers: List<KPLayer>,

    /**
     * Framerate in frames per second
     */
    var fr: JsonElement? = null,

    /**
     * Extra composition
     */
    var xt: Long? = null
)

/**
 * Scale factor, `[100, 100]` for no scaling
 *
 * End point for the gradient
 *
 * Starting point for the gradient
 *
 * Group alignment
 *
 * An animatable property that holds an array of numbers
 */
@Serializable
data class MultiDimensional(
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null
)

/**
 * Layer transform
 */
@Serializable
data class Transform(
    /**
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    var a: PositionProperty? = null,

    var o: Value? = null,

    /**
     * Scale factor, `[100, 100]` for no scaling
     */
    var s: MultiDimensional? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    var sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    var sk: Value? = null,

    /**
     * Position / Translation
     *
     * Position / Translation with split components
     */
    var p: TransformP? = null,

    /**
     * Rotation in degrees, clockwise
     */
    var r: Value? = null,

    var or: MultiDimensional? = null,

    /**
     * Split rotation component
     */
    var rx: Value? = null,

    /**
     * Split rotation component
     */
    var ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    var rz: Value? = null
)

/**
 * Anchor point: a position (relative to its parent) around which transformations are
 * applied (ie: center for rotation / scale)
 *
 * Position / Translation
 *
 * Center of the rectangle
 *
 * An animatable property to represent a position in space
 */
@Serializable
data class PositionProperty(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null,

    var x: String? = null
)

/**
 * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
 * the Y axis)
 *
 * Skew amount as an angle in degrees
 *
 * Rotation in degrees, clockwise
 *
 * Split rotation component
 *
 * Split rotation component, equivalent to `r` when not split
 *
 * Local light angle
 *
 * Layer knowck out drop shadow
 *
 * Blur size
 *
 * Local lighting angle
 *
 * Use global light
 *
 * Local lighting altitude
 *
 * Align with layer
 *
 * Opacity, 100 means fully opaque
 *
 * Highlight Angle, relative to the direction from `s` to `e`
 *
 * Highlight Length, as a percentage between `s` and `e`
 *
 * Length of the dash
 *
 * Animatable alternative to ml
 *
 * Stroke width
 *
 * Outer Roundness as a percentage
 *
 * Rotation, clockwise in degrees
 *
 * Amount as a percentage
 *
 * Rounded corners radius
 *
 * Number of copies
 *
 * Opacity of the last repeated object.
 *
 * Opacity of the first repeated object.
 *
 * Segment end
 *
 * Segment start
 *
 * Point type (1 = corner, 2 = smooth)
 *
 * Number of ridges per segment
 *
 * Distance between peaks and troughs
 *
 * Distance from the Z=0 plane.
 * Small values yield a higher perspective effect.
 *
 * An animatable property that holds a float
 *
 * An animatable property that holds an array of numbers
 */
@Serializable
data class Value(
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null
)

/**
 * Anchor point: a position (relative to its parent) around which transformations are
 * applied (ie: center for rotation / scale)
 *
 * Position / Translation
 *
 * Center of the rectangle
 *
 * An animatable property to represent a position in space
 *
 * Position / Translation with split components
 *
 * An animatable property that is split into individually anaimated components
 */
@Serializable
data class TransformP(
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null,

    //@Transient
    var x: JsonElement? = null,
    var s: Boolean? = null,
    var y: Value? = null,
    var z: Value? = null
)

@Serializable
sealed class X {
    class StringValue(var value: String) : X()
    class ValueValue(var value: Value) : X()
}

/**
 * Bezier shape used to mask/clip a layer
 */
@Serializable
data class Mask(
    /**
     * Match name, used in expressions
     */
    var mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    var nm: String? = null,

    var inv: Boolean? = null,
    var mode: String? = null,
    var o: Value? = null,
    var pt: ShapeProperty? = null,
    var x: Value? = null
)

/**
 * Bezier path
 *
 * An animatable property that holds a Bezier
 */
@Serializable
data class ShapeProperty(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null
)

/**
 * List of valid shapes
 *
 * Ellipse shape
 *
 * Drawable shape
 *
 * Base class for all elements of ShapeLayer and Group
 *
 *
 *
 * Solid fill color
 *
 * Gradient fill
 *
 * Gradient stroke
 *
 * Shape Element that can contain other shapes
 *
 * Animatable Bezier curve
 *
 * Star or regular polygon
 *
 * Interpolates the shape with its center point and bezier tangents with the opposite
 * direction
 *
 * A simple rectangle shape
 *
 * Duplicates previous shapes in a group
 *
 * Rounds corners of other shapes
 *
 * Solid stroke
 *
 * Group transform
 *
 * Layer transform
 *
 * Trims shapes into a segment
 *
 * Boolean operator on shapes
 *
 * Changes the edges of affected shapes into a series of peaks and valleys of uniform size
 *
 * Represents a style for shapes without fill or stroke
 */
@Serializable
data class ShapeList(
    /**
     * NOTE : added manually by @xaba after looking at animations on lottieFiles
     * index of something...
     */
    var ind: Long? = null,

    /**
     * Match name, used in expressions
     */
    var mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    var nm: String? = null,

    var bm: Long? = null,

    /**
     * CSS class used by the SVG renderer
     */
    var cl: String? = null,

    /**
     * Whether the shape is hidden
     */
    var hd: Boolean? = null,

    /**
     * Index used in expressions
     */
    var ix: Long? = null,

    /**
     * `id` attribute used by the SVG renderer
     */
    var ln: String? = null,

    var ty: String,

    /**
     * Direction the shape is drawn as, mostly relevant when using trim path
     *
     * Dashed line definition
     */
    var d: JsonElement? = null,

    /**
     * Center of the rectangle
     *
     * Position / Translation
     *
     * Position / Translation with split components
     */
    var p: ShapeP? = null,

    /**
     * Starting point for the gradient
     *
     * Scale factor, `[100, 100]` for no scaling
     *
     * Segment start
     *
     * Distance between peaks and troughs
     */
    var s: EClass? = null,

    /**
     * Number of copies
     *
     * Stroke color
     */
    var c: CClass? = null,

    /**
     * Opacity, 100 means fully opaque
     */
    var o: Value? = null,

    /**
     * Rotation, clockwise in degrees
     *
     * Rounded corners radius
     *
     * Rotation in degrees, clockwise
     *
     * Number of ridges per segment
     */
    var r: JsonElement? = null,

    /**
     * Highlight Angle, relative to the direction from `s` to `e`
     *
     * Amount as a percentage
     *
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    var a: AClass? = null,

    /**
     * End point for the gradient
     *
     * Segment end
     */
    var e: EClass? = null,

    /**
     * Gradient colors
     */
    var g: GradientColors? = null,

    /**
     * Highlight Length, as a percentage between `s` and `e`
     */
    var h: Value? = null,

    /**
     * Type of the gradient
     */
    var t: Long? = null,

    var lc: Long? = null,
    var lj: Long? = null,
    var ml: JsonElement? = null,

    /**
     * Animatable alternative to ml
     */
    var ml2: Value? = null,

    /**
     * Stroke width
     */
    var w: Value? = null,

    /**
     * Index used in expressions
     */
    var cix: Long? = null,

    var it: List<ShapeList>? = null,
    var np: Int? = null,

    /**
     * Bezier path
     */
    var ks: ShapeProperty? = null,

    var or: EClass? = null,

    /**
     * Outer Roundness as a percentage
     */
    var os: Value? = null,

    /**
     * Point type (1 = corner, 2 = smooth)
     */
    var pt: Value? = null,

    /**
     * Star type, `1` for Star, `2` for Polygon
     */
    var sy: Long? = null,

    /**
     * Stacking order
     *
     * How to treat multiple copies
     */
    var m: Long? = null,

    /**
     * Transform applied to each copy
     */
    var tr: RepeaterTransformClass? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    var sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    var sk: Value? = null,

    /**
     * Split rotation component
     */
    var rx: Value? = null,

    /**
     * Split rotation component
     */
    var ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    var rz: Value? = null,

    var mm: Long? = null
)

/**
 * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
 * the Y axis)
 *
 * Skew amount as an angle in degrees
 *
 * Rotation in degrees, clockwise
 *
 * Split rotation component
 *
 * Split rotation component, equivalent to `r` when not split
 *
 * Local light angle
 *
 * Layer knowck out drop shadow
 *
 * Blur size
 *
 * Local lighting angle
 *
 * Use global light
 *
 * Local lighting altitude
 *
 * Align with layer
 *
 * Opacity, 100 means fully opaque
 *
 * Highlight Angle, relative to the direction from `s` to `e`
 *
 * Highlight Length, as a percentage between `s` and `e`
 *
 * Length of the dash
 *
 * Animatable alternative to ml
 *
 * Stroke width
 *
 * Outer Roundness as a percentage
 *
 * Rotation, clockwise in degrees
 *
 * Amount as a percentage
 *
 * Rounded corners radius
 *
 * Number of copies
 *
 * Opacity of the last repeated object.
 *
 * Opacity of the first repeated object.
 *
 * Segment end
 *
 * Segment start
 *
 * Point type (1 = corner, 2 = smooth)
 *
 * Number of ridges per segment
 *
 * Distance between peaks and troughs
 *
 * Distance from the Z=0 plane.
 * Small values yield a higher perspective effect.
 *
 * An animatable property that holds a float
 *
 * An animatable property that holds an array of numbers
 *
 * Anchor point: a position (relative to its parent) around which transformations are
 * applied (ie: center for rotation / scale)
 *
 * Position / Translation
 *
 * Center of the rectangle
 *
 * An animatable property to represent a position in space
 */
@Serializable
data class AClass(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null
)

/**
 * Stroke color
 *
 * An animatable property that holds a Color
 *
 * An animatable property that holds an array of numbers
 *
 * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
 * the Y axis)
 *
 * Skew amount as an angle in degrees
 *
 * Rotation in degrees, clockwise
 *
 * Split rotation component
 *
 * Split rotation component, equivalent to `r` when not split
 *
 * Local light angle
 *
 * Layer knowck out drop shadow
 *
 * Blur size
 *
 * Local lighting angle
 *
 * Use global light
 *
 * Local lighting altitude
 *
 * Align with layer
 *
 * Opacity, 100 means fully opaque
 *
 * Highlight Angle, relative to the direction from `s` to `e`
 *
 * Highlight Length, as a percentage between `s` and `e`
 *
 * Length of the dash
 *
 * Animatable alternative to ml
 *
 * Stroke width
 *
 * Outer Roundness as a percentage
 *
 * Rotation, clockwise in degrees
 *
 * Amount as a percentage
 *
 * Rounded corners radius
 *
 * Number of copies
 *
 * Opacity of the last repeated object.
 *
 * Opacity of the first repeated object.
 *
 * Segment end
 *
 * Segment start
 *
 * Point type (1 = corner, 2 = smooth)
 *
 * Number of ridges per segment
 *
 * Distance between peaks and troughs
 *
 * Distance from the Z=0 plane.
 * Small values yield a higher perspective effect.
 *
 * An animatable property that holds a float
 *
 * Scale factor, `[100, 100]` for no scaling
 *
 * End point for the gradient
 *
 * Starting point for the gradient
 *
 * Group alignment
 */
@Serializable
data class CClass(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null
)

@Serializable
sealed class D {
    class IntegerValue(var value: Long) : D()
    class StrokeDashArrayValue(var value: List<StrokeDash>) : D()
}

/**
 * An item used to described the dashe pattern in a stroked path
 */
@Serializable
data class StrokeDash(
    /**
     * Match name, used in expressions
     */
    var mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    var nm: String? = null,

    var n: String? = null,

    /**
     * Length of the dash
     */
    var v: Value? = null
)

/**
 * Scale factor, `[100, 100]` for no scaling
 *
 * End point for the gradient
 *
 * Starting point for the gradient
 *
 * Group alignment
 *
 * An animatable property that holds an array of numbers
 *
 * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
 * the Y axis)
 *
 * Skew amount as an angle in degrees
 *
 * Rotation in degrees, clockwise
 *
 * Split rotation component
 *
 * Split rotation component, equivalent to `r` when not split
 *
 * Local light angle
 *
 * Layer knowck out drop shadow
 *
 * Blur size
 *
 * Local lighting angle
 *
 * Use global light
 *
 * Local lighting altitude
 *
 * Align with layer
 *
 * Opacity, 100 means fully opaque
 *
 * Highlight Angle, relative to the direction from `s` to `e`
 *
 * Highlight Length, as a percentage between `s` and `e`
 *
 * Length of the dash
 *
 * Animatable alternative to ml
 *
 * Stroke width
 *
 * Outer Roundness as a percentage
 *
 * Rotation, clockwise in degrees
 *
 * Amount as a percentage
 *
 * Rounded corners radius
 *
 * Number of copies
 *
 * Opacity of the last repeated object.
 *
 * Opacity of the first repeated object.
 *
 * Segment end
 *
 * Segment start
 *
 * Point type (1 = corner, 2 = smooth)
 *
 * Number of ridges per segment
 *
 * Distance between peaks and troughs
 *
 * Distance from the Z=0 plane.
 * Small values yield a higher perspective effect.
 *
 * An animatable property that holds a float
 */
@Serializable
data class EClass(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null
)

/**
 * Represents colors and offsets in a gradient
 *
 * Colors are represented as a flat list interleaving offsets and color components in weird
 * ways
 * There are two possible layouts:
 *
 * Without alpha, the colors are a sequence of offset, r, g, b
 *
 * With alpha, same as above but at the end of the list there is a sequence of offset,
 * alpha
 *
 * Gradient colors
 */
@Serializable
data class GradientColors(
    var k: MultiDimensional,

    /**
     * Number of colors in `k`
     */
    var p: Long
)

@Serializable
sealed class Ml {
    class IntValue(var value: Int) : Ml()
    class ValueValue(var value: Value) : Ml()
}

/**
 * Anchor point: a position (relative to its parent) around which transformations are
 * applied (ie: center for rotation / scale)
 *
 * Position / Translation
 *
 * Center of the rectangle
 *
 * An animatable property to represent a position in space
 *
 * Position / Translation with split components
 *
 * An animatable property that is split into individually anaimated components
 */
@Serializable
data class ShapeP(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    var l: Long? = null,

    //@Transient
    var x: X? = null,
    var s: Boolean? = null,
    var y: Value? = null,
    var z: Value? = null
)

@Serializable
sealed class R {
    class IntegerValue(var value: Long) : R()
    class ValueValue(var value: Value) : R()
}

/**
 * Transform applied to each copy
 *
 * Transform used by a repeater, the transform is applied to each subsequent repeated
 * object.
 *
 * Layer transform
 */
@Serializable
data class RepeaterTransformClass(
    /**
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    var a: PositionProperty? = null,

    var o: Value? = null,

    /**
     * Scale factor, `[100, 100]` for no scaling
     */
    var s: MultiDimensional? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    var sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    var sk: Value? = null,

    /**
     * Position / Translation
     *
     * Position / Translation with split components
     */
    var p: TransformP? = null,

    /**
     * Rotation in degrees, clockwise
     */
    var r: Value? = null,

    var or: MultiDimensional? = null,

    /**
     * Split rotation component
     */
    var rx: Value? = null,

    /**
     * Split rotation component
     */
    var ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    var rz: Value? = null,

    /**
     * Opacity of the last repeated object.
     */
    var eo: Value? = null,

    /**
     * Opacity of the first repeated object.
     */
    var so: Value? = null
)

/**
 * Stroke color
 *
 * An animatable property that holds a Color
 *
 * An animatable property that holds an array of numbers
 */
@Serializable
data class ColorValue(
    //@Transient
    var k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    var a: Long? = null,

    var ix: Long? = null,
    var x: String? = null
)

@Serializable
sealed class T {
    class IntegerValue(var value: Long) : T()
    class StringValue(var value: String) : T()
}

/**
 * Defines character shapes
 */
@Serializable
data class CharacterData(
    var ch: String,
    var data: Data,
    var fFamily: String,
    var size: Int,
    var style: String,
    var w: Int
)

/**
 * Defines a character as shapes
 *
 * Defines a character as a precomp layer
 */
@Serializable
data class Data(
    /**
     * Shapes forming the character
     */
    var shapes: List<ShapeList>? = null,

    /**
     * Frame when the layer becomes visible
     */
    var ip: JsonElement? = null,

    /**
     * Layer transform
     */
    var ks: Transform? = null,

    /**
     * Frame when the layer becomes invisible
     */
    var op: JsonElement? = null,

    /**
     * ID of the precomp as specified in the assets
     */
    @SerialName("refId")
    var refID: String? = null,

    var sr: JsonElement? = null,
    var st: JsonElement? = null
)

/**
 * Asset containing an animation that can be referenced by layers.
 *
 *
 *
 * Base class for layer holders
 */
@Serializable
data class Precomposition(
    /**
     * Unique identifier used by layers when referencing this asset
     */
    var id: String,

    /**
     * Human readable name
     */
    var nm: String? = null,

    @Serializable(with = KPLayerListSerializer::class)
    var layers: List<KPLayer>,

    /**
     * Framerate in frames per second
     */
    var fr: JsonElement? = null,

    /**
     * Extra composition
     */
    var xt: Long? = null
)

/**
 * List of fonts
 */
@Serializable
data class FontList(
    var list: List<Font>? = null
)

/**
 * Describes how a font with given settings should be loaded
 */
@Serializable
data class Font(
    /**
     * Text will be moved down based on this value
     */
    var ascent: JsonElement? = null,

    /**
     * CSS Class applied to text objects using this font
     */
    var fClass: String? = null,

    var fFamily: String,

    /**
     * Name used by text documents to reference this font, usually it's `fFamily` followed by
     * `fStyle`
     */
    var fName: String,

    var fPath: String? = null,
    var fStyle: String,
    var fWeight: String? = null,
    var origin: Long? = null
)

/**
 * Defines named portions of the composition.
 */
@Serializable
data class Marker(
    var cm: String? = null,
    var dr: Int? = null,
    var tm: Int? = null
)

/**
 * Motion blur settings
 */
@Serializable
data class MotionBlur(
    var asl: Int? = null,

    /**
     * Angle in degrees
     */
    var sa: Int? = null,

    /**
     * Angle in degrees
     */
    var sp: Int? = null,

    var spf: Int? = null
)

/**
 * Document metadata
 */
@Serializable
data class Metadata(
    var a: String? = null,
    var d: String? = null,

    /**
     * Software used to generate the file
     */
    var g: String? = null,

    var tc: String? = null,
    //@Transient
    var k: JsonElement? = null
)

@Serializable
sealed class K {
    class StringArrayValue(var value: List<String>) : K()
    class StringValue(var value: String) : K()
}

/**
 * User-defined metadata
 */
@Serializable
data class UserMetadata(
    var customProps: JsonElement? = null,
    var filename: String? = null
)
