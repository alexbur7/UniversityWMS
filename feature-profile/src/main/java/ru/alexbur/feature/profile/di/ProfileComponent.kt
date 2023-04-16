package ru.alexbur.feature.profile.di

import dagger.Component
import ru.alexbur.core.di.named.FeatureScope
import ru.alexbur.core.domain.manager.CommonComponentManager
import ru.alexbur.core.domain.mediators.CommonComponentMediator
import ru.alexbur.feature.profile.presentation.ProfileViewModel

@FeatureScope
@Component(
    dependencies = [CommonComponentMediator::class],
    modules = [ProfileModule::class]
)
interface ProfileComponent {

    fun getViewModel(): ProfileViewModel

    @Component.Builder
    interface Builder {
        fun commonComponent(commonComponentMediator: CommonComponentMediator): Builder
        fun build(): ProfileComponent
    }

    companion object {

        @Volatile
        private var component: ProfileComponent? = null

        fun getComponent(): ProfileComponent =
            component ?: synchronized(this) {
                component ?: DaggerProfileComponent.builder()
                    .commonComponent(CommonComponentManager.component)
                    .build()
                    .also { component = it }
            }
    }
}