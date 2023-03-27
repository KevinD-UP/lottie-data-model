package lottieAnimation

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.serialization.json.JsonElement
import lottieAnimation.layer.Layer
import lottieAnimation.layer.serializers.LayerListSerializer

/**
 * Top level object, describing the animation
 *
 *
 *
 * Base class for layer holders
 */
@Serializable
data class LottieAnimation (
    /**
     * Match name, used in expressions
     */
    val mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    val nm: String? = null,

    /**
     * List of assets that can be referenced by layers
     */
    val assets: List<Asset>? = null,

    /**
     * Data defining text characters as lottie shapes. If present a player might only render
     * characters defined here and nothing else.
     */
    val chars: List<CharacterData>? = null,

    /**
     * List of Extra compositions not referenced by anything
     */
    val comps: List<Precomposition>? = null,

    /**
     * Whether the animation has 3D layers
     */
    val ddd: Long? = null,

    val fonts: FontList? = null,

    /**
     * Framerate in frames per second
     */
    val fr: Int,

    /**
     * Height of the animation
     */
    val h: Long,

    /**
     * "In Point", which frame the animation starts at (usually 0)
     */
    val ip: Int,

    /**
     * Markers defining named sections of the composition.
     */
    val markers: List<Marker>? = null,

    val mb: MotionBlur? = null,

    /**
     * Document metadata
     */
    val meta: Metadata? = null,

    val metadata: UserMetadata? = null,

    /**
     * "Out Point", which frame the animation stops/loops at, which makes this the duration in
     * frames when `ip` is 0
     */
    val op: Int,

    val v: String? = null,

    /**
     * Width of the animation
     */
    val w: Long,

    @Serializable(with = LayerListSerializer::class)
    val layers: List<Layer>
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
data class Asset (
    /**
     * Unique identifier used by layers when referencing this asset
     */
    val id: String,

    /**
     * Human readable name
     */
    val nm: String? = null,

    /**
     * Whether the file is embedded
     */
    val e: Long? = null,

    /**
     * Filename or data url
     */
    val p: String? = null,

    /**
     * Path to the directory containing a file
     */
    val u: String? = null,

    /**
     * Height of the image
     */
    val h: Int? = null,

    /**
     * Marks as part of an image sequence if present
     */
    val t: JsonElement? = null,

    /**
     * Width of the image
     */
    val w: Int? = null,

    @Serializable(with = LayerListSerializer::class)
    val layers: List<Layer>,

    /**
     * Framerate in frames per second
     */
    val fr: Int? = null,

    /**
     * Extra composition
     */
    val xt: Long? = null
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
data class MultiDimensional (
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null
)

/**
 * Layer transform
 */
@Serializable
data class Transform (
    /**
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    val a: PositionProperty? = null,

    val o: Value? = null,

    /**
     * Scale factor, `[100, 100]` for no scaling
     */
    val s: MultiDimensional? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    val sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    val sk: Value? = null,

    /**
     * Position / Translation
     *
     * Position / Translation with split components
     */
    val p: TransformP? = null,

    /**
     * Rotation in degrees, clockwise
     */
    val r: Value? = null,

    val or: MultiDimensional? = null,

    /**
     * Split rotation component
     */
    val rx: Value? = null,

    /**
     * Split rotation component
     */
    val ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    val rz: Value? = null
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
data class PositionProperty (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null,

    val x: String? = null
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
data class Value (
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null
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
data class TransformP (
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null,

    //@Transient
    val x: JsonElement? = null,
    val s: Boolean? = null,
    val y: Value? = null,
    val z: Value? = null
)

@Serializable
sealed class X {
    class StringValue(val value: String) : X()
    class ValueValue(val value: Value)   : X()
}

/**
 * Bezier shape used to mask/clip a layer
 */
@Serializable
data class Mask (
    /**
     * Match name, used in expressions
     */
    val mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    val nm: String? = null,

    val inv: Boolean? = null,
    val mode: String? = null,
    val o: Value? = null,
    val pt: ShapeProperty? = null,
    val x: Value? = null
)

/**
 * Bezier path
 *
 * An animatable property that holds a Bezier
 */
@Serializable
data class ShapeProperty (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null
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
data class ShapeList (
    /**
     * NOTE : added manually by @xaba after looking at animations on lottieFiles
     * index of something...
     */
    val ind: Long? = null,

    /**
     * Match name, used in expressions
     */
    val mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    val nm: String? = null,

    val bm: Long? = null,

    /**
     * CSS class used by the SVG renderer
     */
    val cl: String? = null,

    /**
     * Whether the shape is hidden
     */
    val hd: Boolean? = null,

    /**
     * Index used in expressions
     */
    val ix: Long? = null,

    /**
     * `id` attribute used by the SVG renderer
     */
    val ln: String? = null,

    val ty: String,

    /**
     * Direction the shape is drawn as, mostly relevant when using trim path
     *
     * Dashed line definition
     */
    val d: JsonElement? = null,

    /**
     * Center of the rectangle
     *
     * Position / Translation
     *
     * Position / Translation with split components
     */
    val p: ShapeP? = null,

    /**
     * Starting point for the gradient
     *
     * Scale factor, `[100, 100]` for no scaling
     *
     * Segment start
     *
     * Distance between peaks and troughs
     */
    val s: EClass? = null,

    /**
     * Number of copies
     *
     * Stroke color
     */
    val c: CClass? = null,

    /**
     * Opacity, 100 means fully opaque
     */
    val o: Value? = null,

    /**
     * Rotation, clockwise in degrees
     *
     * Rounded corners radius
     *
     * Rotation in degrees, clockwise
     *
     * Number of ridges per segment
     */
    val r: JsonElement? = null,

    /**
     * Highlight Angle, relative to the direction from `s` to `e`
     *
     * Amount as a percentage
     *
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    val a: AClass? = null,

    /**
     * End point for the gradient
     *
     * Segment end
     */
    val e: EClass? = null,

    /**
     * Gradient colors
     */
    val g: GradientColors? = null,

    /**
     * Highlight Length, as a percentage between `s` and `e`
     */
    val h: Value? = null,

    /**
     * Type of the gradient
     */
    val t: Long? = null,

    val lc: Long? = null,
    val lj: Long? = null,
    val ml: JsonElement? = null,

    /**
     * Animatable alternative to ml
     */
    val ml2: Value? = null,

    /**
     * Stroke width
     */
    val w: Value? = null,

    /**
     * Index used in expressions
     */
    val cix: Long? = null,

    val it: List<ShapeList>? = null,
    val np: Int? = null,

    /**
     * Bezier path
     */
    val ks: ShapeProperty? = null,

    val or: EClass? = null,

    /**
     * Outer Roundness as a percentage
     */
    val os: Value? = null,

    /**
     * Point type (1 = corner, 2 = smooth)
     */
    val pt: Value? = null,

    /**
     * Star type, `1` for Star, `2` for Polygon
     */
    val sy: Long? = null,

    /**
     * Stacking order
     *
     * How to treat multiple copies
     */
    val m: Long? = null,

    /**
     * Transform applied to each copy
     */
    val tr: RepeaterTransformClass? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    val sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    val sk: Value? = null,

    /**
     * Split rotation component
     */
    val rx: Value? = null,

    /**
     * Split rotation component
     */
    val ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    val rz: Value? = null,

    val mm: Long? = null
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
data class AClass (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null
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
data class CClass (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null
)

@Serializable
sealed class D {
    class IntegerValue(val value: Long)                     : D()
    class StrokeDashArrayValue(val value: List<StrokeDash>) : D()
}

/**
 * An item used to described the dashe pattern in a stroked path
 */
@Serializable
data class StrokeDash (
    /**
     * Match name, used in expressions
     */
    val mn: String? = null,

    /**
     * Name, as seen from editors and the like
     */
    val nm: String? = null,

    val n: String? = null,

    /**
     * Length of the dash
     */
    val v: Value? = null
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
data class EClass (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null
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
data class GradientColors (
    val k: MultiDimensional,

    /**
     * Number of colors in `k`
     */
    val p: Long
)

@Serializable
sealed class Ml {
    class IntValue(val value: Int) : Ml()
    class ValueValue(val value: Value)   : Ml()
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
data class ShapeP (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,

    /**
     * Number of components in the value arrays.
     * If present values will be truncated or expanded to match this length when accessed from
     * expressions.
     */
    val l: Long? = null,

    //@Transient
    val x: X? = null,
    val s: Boolean? = null,
    val y: Value? = null,
    val z: Value? = null
)

@Serializable
sealed class R {
    class IntegerValue(val value: Long) : R()
    class ValueValue(val value: Value)  : R()
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
data class RepeaterTransformClass (
    /**
     * Anchor point: a position (relative to its parent) around which transformations are
     * applied (ie: center for rotation / scale)
     */
    val a: PositionProperty? = null,

    val o: Value? = null,

    /**
     * Scale factor, `[100, 100]` for no scaling
     */
    val s: MultiDimensional? = null,

    /**
     * Direction along which skew is applied, in degrees (`0` skews along the X axis, `90` along
     * the Y axis)
     */
    val sa: Value? = null,

    /**
     * Skew amount as an angle in degrees
     */
    val sk: Value? = null,

    /**
     * Position / Translation
     *
     * Position / Translation with split components
     */
    val p: TransformP? = null,

    /**
     * Rotation in degrees, clockwise
     */
    val r: Value? = null,

    val or: MultiDimensional? = null,

    /**
     * Split rotation component
     */
    val rx: Value? = null,

    /**
     * Split rotation component
     */
    val ry: Value? = null,

    /**
     * Split rotation component, equivalent to `r` when not split
     */
    val rz: Value? = null,

    /**
     * Opacity of the last repeated object.
     */
    val eo: Value? = null,

    /**
     * Opacity of the first repeated object.
     */
    val so: Value? = null
)

/**
 * Stroke color
 *
 * An animatable property that holds a Color
 *
 * An animatable property that holds an array of numbers
 */
@Serializable
data class ColorValue (
    //@Transient
    val k: JsonElement? = null,

    /**
     * Whether the property is animated
     */
    val a: Long? = null,

    val ix: Long? = null,
    val x: String? = null
)

@Serializable
sealed class T {
    class IntegerValue(val value: Long)  : T()
    class StringValue(val value: String) : T()
}

/**
 * Defines character shapes
 */
@Serializable
data class CharacterData (
    val ch: String,
    val data: Data,
    val fFamily: String,
    val size: Int,
    val style: String,
    val w: Int
)

/**
 * Defines a character as shapes
 *
 * Defines a character as a precomp layer
 */
@Serializable
data class Data (
    /**
     * Shapes forming the character
     */
    val shapes: List<ShapeList>? = null,

    /**
     * Frame when the layer becomes visible
     */
    val ip: Int? = null,

    /**
     * Layer transform
     */
    val ks: Transform? = null,

    /**
     * Frame when the layer becomes invisible
     */
    val op: Int? = null,

    /**
     * ID of the precomp as specified in the assets
     */
    @SerialName("refId")
    val refID: String? = null,

    val sr: Int? = null,
    val st: Int? = null
)

/**
 * Asset containing an animation that can be referenced by layers.
 *
 *
 *
 * Base class for layer holders
 */
@Serializable
data class Precomposition (
    /**
     * Unique identifier used by layers when referencing this asset
     */
    val id: String,

    /**
     * Human readable name
     */
    val nm: String? = null,

    @Serializable(with = LayerListSerializer::class)
    val layers: List<Layer>,

    /**
     * Framerate in frames per second
     */
    val fr: Int? = null,

    /**
     * Extra composition
     */
    val xt: Long? = null
)

/**
 * List of fonts
 */
@Serializable
data class FontList (
    val list: List<Font>? = null
)

/**
 * Describes how a font with given settings should be loaded
 */
@Serializable
data class Font (
    /**
     * Text will be moved down based on this value
     */
    val ascent: Int? = null,

    /**
     * CSS Class applied to text objects using this font
     */
    val fClass: String? = null,

    val fFamily: String,

    /**
     * Name used by text documents to reference this font, usually it's `fFamily` followed by
     * `fStyle`
     */
    val fName: String,

    val fPath: String? = null,
    val fStyle: String,
    val fWeight: String? = null,
    val origin: Long? = null
)

/**
 * Defines named portions of the composition.
 */
@Serializable
data class Marker (
    val cm: String? = null,
    val dr: Int? = null,
    val tm: Int? = null
)

/**
 * Motion blur settings
 */
@Serializable
data class MotionBlur (
    val asl: Int? = null,

    /**
     * Angle in degrees
     */
    val sa: Int? = null,

    /**
     * Angle in degrees
     */
    val sp: Int? = null,

    val spf: Int? = null
)

/**
 * Document metadata
 */
@Serializable
data class Metadata (
    val a: String? = null,
    val d: String? = null,

    /**
     * Software used to generate the file
     */
    val g: String? = null,

    val tc: String? = null,
    //@Transient
    val k: JsonElement? = null
)

@Serializable
sealed class K {
    class StringArrayValue(val value: List<String>) : K()
    class StringValue(val value: String)            : K()
}

/**
 * User-defined metadata
 */
@Serializable
data class UserMetadata (
    val customProps: JsonElement? = null,
    val filename: String? = null
)
