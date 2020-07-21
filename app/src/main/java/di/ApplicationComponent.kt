package di

import android.app.Application
import dagger.Component
import ru.gymbay.cats.MainActivity

@Component
interface ApplicationComponent {
    // This tells Dagger that LoginActivity requests injection so the graph needs to
    // satisfy all the dependencies of the fields that LoginActivity is requesting.
    fun inject(activity: MainActivity)
}

// appComponent lives in the Application class to share its lifecycle
class MyApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val applicationComponent: ApplicationComponent = DaggerApplicationComponent.create()
}