import android.content.Context
import com.maverick.themelancholy.model.MelancholyDatabase

val DB_NAME = "melancholydb"

fun buildDb(context: Context): MelancholyDatabase {
    return MelancholyDatabase.buildDatabase(context)
}