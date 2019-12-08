# SkunkworksFunctional

## Description ##
So there are two Fragments and a single Activity. 
- MainActivity
- NewsListFragment
- NewsDetailFragment

## Failures

When running NetworkServiceIntegrationTests we get an IllegalArgumentException: 
```
Cannot serialize Kotlin type com.memetoclasm.skunkworksfunctional.algebras.data.network.dto.NewsResponse. 
Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. 
Please use KotlinJsonAdapter from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact.
```

java.lang.IllegalArgumentException: Cannot serialize Kotlin type com.memetoclasm.skunkworksfunctional.algebras.data.network.dto.NewsResponse. Reflective serialization of Kotlin classes without using kotlin-reflect has undefined and unexpected behavior. Please use KotlinJsonAdapter from the moshi-kotlin artifact or use code gen from the moshi-kotlin-codegen artifact.
