package ru.alexbur.university_wms.di

import dagger.Component
import ru.alexbur.core.di.named.MainScope
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.university_wms.MainActivity
import ru.alexbur.university_wms.presentation.navbar.BottomNavBarViewModel

@MainScope
@Component(
    dependencies = [CommonComponentMediator::class],
    modules = [NavigationFactoriesModule::class]
)
interface MainComponent {

    fun getViewModel(): BottomNavBarViewModel

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun commonComponent(commonComponentMediator: CommonComponentMediator): Builder
        fun build(): MainComponent
    }

    companion object {

        @Volatile
        private var component: MainComponent? = null

        fun getComponent(): MainComponent =
            component ?: synchronized(this) {
                component ?: DaggerMainComponent.builder()
                    .commonComponent(CommonComponentManager.component)
                    .build()
                    .also { component = it }
            }
    }
}