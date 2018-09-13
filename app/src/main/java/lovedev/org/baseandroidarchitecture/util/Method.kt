
import android.util.Base64
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Kevin
 * @data 2018/8/9
 */
fun any2ByteArray(any: Any) = Gson().toJson(any).toByteArray()

fun toString(any: Any) = Gson().toJson(any).toString()
fun <T> str2Obj(src: String, clazz: Class<T>): T = Gson().fromJson(src, clazz)
inline fun <reified T> fromJson(json: String): List<T> = Gson().fromJson(json, object : TypeToken<T>() {}.type)
inline fun <reified T> fromJson2List(json: String): List<T> = Gson().fromJson(json, object : TypeToken<List<T>>() {}.type)


fun encodeToString(content: String) = Base64.encodeToString(content.toByteArray(), Base64.DEFAULT)
fun decode(content: ByteArray) = Base64.decode(content, Base64.DEFAULT).toString(Charsets.UTF_8)
fun decode(content: String) = Base64.decode(content, Base64.DEFAULT).toString(Charsets.UTF_8)
fun toUTF8String(content: ByteArray) = content.toString(Charsets.UTF_8)