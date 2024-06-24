import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.maverick.themelancholy.model.MelancholyDatabase

val DB_NAME = "melancholydb"

fun buildDb(context: Context): MelancholyDatabase {
    return MelancholyDatabase.buildDatabase(context)
}

val MIGRATION_1_2 = object : Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE IF NOT EXISTS UserNewsCrossRef (\n" +
                    "username TEXT NOT NULL,\n" +
                    "id INTEGER NOT NULL,\n" +
                    "PRIMARY KEY(username, id)\n" +
                    ")"
        )
    }
}