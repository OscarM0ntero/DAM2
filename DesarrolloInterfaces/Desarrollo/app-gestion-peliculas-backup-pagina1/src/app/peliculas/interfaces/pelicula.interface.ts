export interface Pelicula {
	id: string;
	title: string;
	original_title: string;
	overview: string;		//Descripcion
	release_date: Date;
	original_language: string;
	vote_average: number;
	vote_count: number;
	poster_path: string;
	backdrop_path: string;
	genre_ids: number[];
	popularity: number;
	video: boolean;
}

export interface Search {
	page: number,
	results: Pelicula[],
	total_pages: number,
	total_results: number
}