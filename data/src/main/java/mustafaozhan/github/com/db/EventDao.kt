package mustafaozhan.github.com.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import mustafaozhan.github.com.model.EventEntity

@Dao
interface EventDao {

    @Query("SELECT * FROM event")
    fun collectFavoriteEvents(): Flow<MutableList<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: EventEntity)
}